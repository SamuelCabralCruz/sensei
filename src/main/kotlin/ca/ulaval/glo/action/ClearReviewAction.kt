package ca.ulaval.glo.action

import ca.ulaval.glo.persistence.review.ReviewPersistence
import com.intellij.codeInsight.daemon.DaemonCodeAnalyzer
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.components.service


class ClearReviewAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        val review = project.service<ReviewPersistence>().state ?: return
        review.comments = mutableMapOf()
        DaemonCodeAnalyzer.getInstance(project).restart()
    }
}
