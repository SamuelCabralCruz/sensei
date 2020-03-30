package ca.ulaval.glo.action

import ca.ulaval.glo.persistence.ReviewPersistence
import ca.ulaval.glo.state.Review
import ca.ulaval.glo.state.ReviewComment
import ca.ulaval.glo.util.getRelativeFilePath
import com.intellij.codeInsight.daemon.DaemonCodeAnalyzer
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.LangDataKeys
import com.intellij.openapi.editor.Caret
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.util.TextRange
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiFile
import java.lang.Integer.max
import java.lang.Integer.min


class AddCommentAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        val caret = e.getData(LangDataKeys.CARET) ?: return
        val editor = e.getData(LangDataKeys.EDITOR) ?: return
        val virtualFile = e.getData(LangDataKeys.VIRTUAL_FILE) ?: return
        val containingFile = e.getData(LangDataKeys.PSI_FILE)?.originalFile ?: return
        val review = ReviewPersistence.getInstance().state ?: return

        val codeSnippet = getCodeSnippet(caret, editor)
        val reviewComment = createReviewComment(project, virtualFile, caret.selectionStartPosition.line, codeSnippet)
        persistReviewComment(review, reviewComment, e, containingFile)
    }

    private fun getCodeSnippet(
        caret: Caret,
        editor: Editor
    ): String {
        val chunkStartLine = caret.selectionStartPosition.line
        val chunkEndLine = caret.selectionEndPosition.line
        val selectionStartLine = max(chunkStartLine - 10, 0)
        val selectionEndLine = min(chunkEndLine + 10, editor.document.lineCount - 1)
        val startOffset = editor.document.getLineStartOffset(selectionStartLine)
        val endOffset = editor.document.getLineEndOffset(selectionEndLine)
        val textRange = TextRange.from(startOffset, endOffset - startOffset)
        val codeSnippet = editor.document.getText(textRange)
        Messages.showMessageDialog(codeSnippet, "sensei", Messages.getInformationIcon())
        return codeSnippet
    }

    private fun createReviewComment(
        project: Project,
        virtualFile: VirtualFile,
        chunkStartLine: Int,
        codeSnippet: String
    ): ReviewComment {
        val reviewComment = ReviewComment()
        reviewComment.filePath = getRelativeFilePath(project, virtualFile)
        reviewComment.startingLine = chunkStartLine + 1
        reviewComment.codeSnippet = codeSnippet
        return reviewComment
    }

    private fun persistReviewComment(
        review: Review,
        reviewComment: ReviewComment,
        e: AnActionEvent,
        containingFile: PsiFile
    ) {
        val reviewComments = review.comments.getOrDefault(reviewComment.filePath, mutableListOf())
        reviewComments.add(reviewComment)
        review.comments[reviewComment.filePath] = reviewComments
        DaemonCodeAnalyzer.getInstance(e.project).restart(containingFile)
    }
}
