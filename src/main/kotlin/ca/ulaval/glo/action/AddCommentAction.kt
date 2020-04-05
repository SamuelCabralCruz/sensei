package ca.ulaval.glo.action

import ca.ulaval.glo.model.Review
import ca.ulaval.glo.model.ReviewComment
import ca.ulaval.glo.persistence.ReviewPersistence
import ca.ulaval.glo.util.getRelativeFilePath
import ca.ulaval.glo.view.dialog.editComment.EditCommentDialog
import com.intellij.codeInsight.daemon.DaemonCodeAnalyzer
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.LangDataKeys
import com.intellij.openapi.components.service
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiFile
import java.lang.Integer.max
import java.lang.Integer.min

class AddCommentAction : AnAction() {
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
        val virtualFile = e.getData(LangDataKeys.VIRTUAL_FILE) ?: return
        val containingFile = e.getData(LangDataKeys.PSI_FILE)?.originalFile ?: return
        val review = project.service<ReviewPersistence>().state ?: return

        val selectionStartingLine = editor.visualToLogicalPosition(caret.selectionStartPosition).line + 1
        val selectionEndingLine = editor.visualToLogicalPosition(caret.selectionEndPosition).line + 1
        val chunkLineRange = IntRange(selectionStartingLine, selectionEndingLine)
        val (startingLine, codeSnippet) = getCodeSnippet(chunkLineRange, editor)
        if (codeSnippet.isEmpty()) return
        val editCommentDialog = EditCommentDialog()
        if (editCommentDialog.showAndGet()) {
            val reviewComment =
                ReviewComment(
                    getRelativeFilePath(project, virtualFile),
                    startingLine,
                    chunkLineRange,
                    codeSnippet,
                    editCommentDialog.getDetails()
                )
            persistReviewComment(review, reviewComment, project, containingFile)
        }
    }

    private fun getCodeSnippet(
        chunkLineRange: IntRange,
        editor: Editor
    ): Pair<Int, String> {
        val selectionStartLine = max(chunkLineRange.first - 10, 1)
        val selectionEndLine = min(chunkLineRange.last + 10, editor.document.lineCount)
        val startOffset = editor.document.getLineStartOffset(selectionStartLine - 1)
        val endOffset = editor.document.getLineEndOffset(selectionEndLine - 1)
        val textRange = TextRange.from(startOffset, endOffset - startOffset)
        return normalizeWhiteSpaces(selectionStartLine, editor.document.getText(textRange))
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

    private fun persistReviewComment(
        review: Review,
        reviewComment: ReviewComment,
        project: Project,
        containingFile: PsiFile
    ) {
        review.addComment(reviewComment)
        DaemonCodeAnalyzer.getInstance(project).restart(containingFile)
    }
}
