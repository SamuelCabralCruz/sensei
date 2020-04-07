package ca.ulaval.glo.action.comment.general

import ca.ulaval.glo.model.review.comment.ReviewGeneralComment
import ca.ulaval.glo.persistence.ReviewPersistence
import ca.ulaval.glo.view.dialog.comment.EditCommentDialog
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.components.service

class AddGeneralCommentAction : AnAction() {
    override fun update(e: AnActionEvent) {
        val project = e.project ?: return
        val review = project.service<ReviewPersistence>().state ?: return
        val presentation = e.presentation
        presentation.isEnabled = review.isOpened()
    }

    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        val review = project.service<ReviewPersistence>().state ?: return

        val editCommentDialog = EditCommentDialog("Add General Comment")
        if (editCommentDialog.showAndGet()) {
            val generalComment = ReviewGeneralComment(editCommentDialog.getDetails())
            review.addGeneralComment(generalComment)
        }
    }
}
