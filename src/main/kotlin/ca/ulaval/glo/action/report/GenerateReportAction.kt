package ca.ulaval.glo.action.report

import ca.ulaval.glo.persistence.ReviewPersistence
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.components.service
import com.intellij.openapi.ui.Messages
import java.awt.Desktop
import java.io.File

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
        val outputPath = "${review.projectBasePath}/sensei/reports"
        review.generateReport(outputPath)
        val reportPath = "$outputPath/index.html"
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            val toURI = File(reportPath).toURI()
            Desktop.getDesktop().browse(toURI)
        } else {
            val reportRelativePath = reportPath.replace(review.projectBasePath!!, "")
            Messages.showInfoMessage(
                "Your report has been successfully created.\nYou can find it here:\n$reportRelativePath",
                "Generate Report"
            )
        }
    }
}
