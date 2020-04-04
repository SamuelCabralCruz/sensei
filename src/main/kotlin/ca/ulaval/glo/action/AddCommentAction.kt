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

        val chunkLineRange = IntRange(caret.selectionStartPosition.line, caret.selectionEndPosition.line)
        val (startingLine, codeSnippet) = getCodeSnippet(chunkLineRange, editor)
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
        val selectionStartLine = max(chunkLineRange.first - 10, 0)
        val selectionEndLine = min(chunkLineRange.last + 10, editor.document.lineCount - 1)
        val startOffset = editor.document.getLineStartOffset(selectionStartLine)
        val endOffset = editor.document.getLineEndOffset(selectionEndLine)
        val textRange = TextRange.from(startOffset, endOffset - startOffset)
        return Pair(selectionStartLine, editor.document.getText(textRange))
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
