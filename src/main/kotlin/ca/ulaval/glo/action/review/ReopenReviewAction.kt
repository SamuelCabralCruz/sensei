package ca.ulaval.glo.action.review

import ca.ulaval.glo.persistence.ReviewPersistence
import com.intellij.codeInsight.daemon.DaemonCodeAnalyzer
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.components.service

class ReopenReviewAction : AnAction() {
    override fun update(e: AnActionEvent) {
        val project = e.project ?: return
        val review = project.service<ReviewPersistence>().state ?: return
        val presentation = e.presentation
        presentation.isEnabled = review.isClosed()
    }

    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        val review = project.service<ReviewPersistence>().state ?: return
        review.reopen()
    }
}
