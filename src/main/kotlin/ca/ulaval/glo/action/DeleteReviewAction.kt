package ca.ulaval.glo.action

import ca.ulaval.glo.persistence.ReviewPersistence
import com.intellij.codeInsight.daemon.DaemonCodeAnalyzer
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.components.service
import com.intellij.openapi.ui.InputValidator
import com.intellij.openapi.ui.Messages

class DeleteReviewAction : AnAction() {
    override fun update(e: AnActionEvent) {
        val project = e.project ?: return
        val review = project.service<ReviewPersistence>().state ?: return
        val presentation = e.presentation
        presentation.isEnabled = review.isCreated()
    }

    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        val review = project.service<ReviewPersistence>().state ?: return
        val evaluationName = review.details.evaluationName!!
        val dialog = Messages.showInputDialog(
            "Are you sure you want to delete this review?\nIf so, write the evaluation name ($evaluationName) to continue.",
            "Delete Review",
            Messages.getWarningIcon(),
            "",
            DisruptiveInputValidator(evaluationName)
        )
        if (dialog != null) {
            review.delete()
            DaemonCodeAnalyzer.getInstance(project).restart()
        }
    }

    private class DisruptiveInputValidator(private val disruptiveValue: String) : InputValidator {
        override fun checkInput(inputString: String?): Boolean {
            return isValid(inputString)
        }

        override fun canClose(inputString: String?): Boolean {
            return isValid(inputString)
        }

        private fun isValid(value: String?): Boolean {
            return value != null && value == disruptiveValue
        }
    }
}
