package ca.ulaval.glo.action

import ca.ulaval.glo.persistence.ReviewPersistence
import com.intellij.codeInsight.daemon.DaemonCodeAnalyzer
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent


class ClearReviewAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val review = ReviewPersistence.getInstance().state ?: return
        review.comments = mutableMapOf()
        DaemonCodeAnalyzer.getInstance(e.project).restart()
    }
}
