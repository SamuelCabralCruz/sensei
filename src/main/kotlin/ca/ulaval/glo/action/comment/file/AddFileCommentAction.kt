package ca.ulaval.glo.action.comment.file

import ca.ulaval.glo.model.review.Review
import ca.ulaval.glo.model.review.comment.ReviewFileComment
import ca.ulaval.glo.persistence.ReviewPersistence
import ca.ulaval.glo.util.getRelativeFilePath
import ca.ulaval.glo.view.dialog.comment.EditCommentDialog
import com.intellij.codeInsight.daemon.DaemonCodeAnalyzer
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.LangDataKeys
import com.intellij.openapi.components.service
import com.intellij.openapi.editor.Document
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiFile
import java.lang.Integer.max
import java.lang.Integer.min

class AddFileCommentAction : AnAction() {
    override fun update(e: AnActionEvent) {
        val project = e.project ?: return
        val review = project.service<ReviewPersistence>().state ?: return
        val presentation = e.presentation
        presentation.isEnabled = review.isOpened()
    }

    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        val caret = e.getData(LangDataKeys.CARET) ?: return
        val editor = e.getData(LangDataKeys.EDITOR) ?: return
        val document = editor.document
        val virtualFile = e.getData(LangDataKeys.VIRTUAL_FILE) ?: return
        val containingFile = e.getData(LangDataKeys.PSI_FILE)?.originalFile ?: return
        val review = project.service<ReviewPersistence>().state ?: return

        val selectionStartingLine = editor.visualToLogicalPosition(caret.selectionStartPosition).line + 1
        val selectionEndingLine = editor.visualToLogicalPosition(caret.selectionEndPosition).line + 1
        val selectionText = getTextForLines(document, selectionStartingLine, selectionEndingLine)
        if (selectionText.isBlank()) return
        val chunkLineRange = IntRange(selectionStartingLine, selectionEndingLine)
        val (startingLine, codeSnippet) = getCodeSnippet(chunkLineRange, document)
        if (codeSnippet.isBlank()) return
        val editCommentDialog = EditCommentDialog()
        if (editCommentDialog.showAndGet()) {
            val reviewComment =
                ReviewFileComment(
                    getRelativeFilePath(project, virtualFile),
                    startingLine,
                    selectionStartingLine,
                    selectionEndingLine,
                    codeSnippet,
                    editCommentDialog.getDetails()
                )
            persistFileComment(review, reviewComment, project, containingFile)
        }
    }

    private fun getCodeSnippet(
        chunkLineRange: IntRange,
        document: Document
    ): Pair<Int, String> {
        val selectionStartLine = max(chunkLineRange.first - 10, 1)
        val selectionEndLine = min(chunkLineRange.last + 10, document.lineCount)
        val codeSnippet = getTextForLines(document, selectionStartLine, selectionEndLine)
        return normalizeWhiteSpaces(selectionStartLine, codeSnippet)
    }

    private fun getTextForLines(
        document: Document,
        startLine: Int,
        endLine: Int
    ): String {
        val startOffset = document.getLineStartOffset(startLine - 1)
        val endOffset = document.getLineEndOffset(endLine - 1)
        val textRange = TextRange.from(startOffset, endOffset - startOffset)
        return document.getText(textRange)
    }

    private fun normalizeWhiteSpaces(startingLine: Int, codeSnippet: String): Pair<Int, String> {
        val (_startingLine, _codeSnippet) = removeHeadingEmptyLines(codeSnippet, startingLine)
        return removeIndent(_startingLine, _codeSnippet)
    }

    private fun removeHeadingEmptyLines(
        codeSnippet: String,
        startingLine: Int
    ): Pair<Int, String> {
        val lines = codeSnippet.split("\n")
        val firstNotEmptyLineIndex = lines.indexOfFirst(fun(line) = line.isNotEmpty())
        val normalizedLines = lines.slice(IntRange(firstNotEmptyLineIndex, lines.lastIndex))
        return Pair(startingLine + firstNotEmptyLineIndex, normalizedLines.joinToString("\n"))
    }

    private fun removeIndent(startingLine: Int, codeSnippet: String): Pair<Int, String> {
        val lines = codeSnippet.split("\n")
        var commonPrefix = lines[0]
        lines.slice(IntRange(1, lines.lastIndex)).forEach(fun(line) {
            if (line.isNotEmpty()) commonPrefix = commonPrefix.commonPrefixWith(line)
        })
        return Pair(
            startingLine,
            lines.joinToString("\n", transform = fun(line): String { return line.removePrefix(commonPrefix) })
        )
    }

    private fun persistFileComment(
        review: Review,
        fileComment: ReviewFileComment,
        project: Project,
        containingFile: PsiFile
    ) {
        review.addComment(fileComment)
        DaemonCodeAnalyzer.getInstance(project).restart(containingFile)
    }
}
