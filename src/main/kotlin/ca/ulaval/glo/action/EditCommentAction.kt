package ca.ulaval.glo.action

import ca.ulaval.glo.persistence.review.ReviewPersistence
import ca.ulaval.glo.persistence.review.state.ReviewComment
import ca.ulaval.glo.view.comment.EditCommentDialog
import com.intellij.codeInsight.daemon.DaemonCodeAnalyzer
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.LangDataKeys
import com.intellij.util.PlatformIcons

class EditCommentAction(private val comment: ReviewComment) : AnAction("Edit comment", "", PlatformIcons.EDIT) {
    override fun actionPerformed(e: AnActionEvent) {
        val review = ReviewPersistence.getInstance().state ?: return
        val containingFile = e.getData(LangDataKeys.PSI_FILE)?.originalFile ?: return
        val updatedComment = comment.clone()
        val editCommentDialog = EditCommentDialog(updatedComment)
        if (editCommentDialog.showAndGet()) {
            updatedComment.details = editCommentDialog.getDetails()
            review.replaceComment(comment, updatedComment)
            DaemonCodeAnalyzer.getInstance(e.project).restart(containingFile)
        }
    }
}
