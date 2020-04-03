package ca.ulaval.glo.action

import ca.ulaval.glo.persistence.ReviewPersistence
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.components.service

class GenerateReportAction : AnAction() {
    override fun update(e: AnActionEvent) {
        val project = e.project ?: return
        val review = project.service<ReviewPersistence>().state ?: return
        val presentation = e.presentation
        presentation.isEnabled = review.isNotEmpty()
    }

    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        val review = project.service<ReviewPersistence>().state ?: return
        review.generateReport()
        // TODO: add open in browser command or popup message to indicate location of report
        // TODO: also add dont show this again (NICE TO HAVE)
    }
}
