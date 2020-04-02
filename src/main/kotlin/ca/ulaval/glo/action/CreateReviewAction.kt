package ca.ulaval.glo.action

import ca.ulaval.glo.persistence.ReviewPersistence
import ca.ulaval.glo.view.dialog.editReview.EditReviewDialog
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.components.service

class CreateReviewAction : AnAction() {
    override fun update(e: AnActionEvent) {
        val project = e.project ?: return
        val review = project.service<ReviewPersistence>().state ?: return
        val presentation = e.presentation
        presentation.isEnabled = !review.isCreated()
    }

    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        val review = project.service<ReviewPersistence>().state ?: return

        val editReviewDialog = EditReviewDialog()
        if (editReviewDialog.showAndGet()) {
            review.create(editReviewDialog.getDetails())
        }
    }
}
