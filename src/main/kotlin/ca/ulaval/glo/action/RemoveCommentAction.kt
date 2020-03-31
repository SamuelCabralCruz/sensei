package ca.ulaval.glo.action

import ca.ulaval.glo.persistence.review.ReviewPersistence
import ca.ulaval.glo.persistence.review.state.ReviewComment
import com.intellij.codeInsight.daemon.DaemonCodeAnalyzer
import com.intellij.icons.AllIcons
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.LangDataKeys
import com.intellij.openapi.components.service
import com.intellij.openapi.ui.Messages

class RemoveCommentAction(private val comment: ReviewComment) :
    AnAction("Remove comment", "", AllIcons.General.Remove) {
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        val review = project.service<ReviewPersistence>().state ?: return
        val containingFile = e.getData(LangDataKeys.PSI_FILE)?.originalFile ?: return
        if (Messages.showOkCancelDialog(
                project,
                "Are you sure you want to delete this comment?",
                "Remove Comment",
                "Ok",
                "Cancel",
                Messages.getWarningIcon()
            ) == Messages.OK
        ) {
            review.removeComment(comment)
            DaemonCodeAnalyzer.getInstance(project).restart(containingFile)
        }
    }
}
