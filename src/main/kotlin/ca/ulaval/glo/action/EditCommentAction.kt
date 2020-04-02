package ca.ulaval.glo.action

import ca.ulaval.glo.persistence.ReviewPersistence
import ca.ulaval.glo.model.ReviewComment
import ca.ulaval.glo.view.dialog.editComment.EditCommentDialog
import com.intellij.codeInsight.daemon.DaemonCodeAnalyzer
import com.intellij.icons.AllIcons
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.LangDataKeys
import com.intellij.openapi.components.service

class EditCommentAction(private val comment: ReviewComment) : AnAction("Edit comment", "", AllIcons.Actions.Edit) {
    override fun update(e: AnActionEvent) {
        val project = e.project ?: return
        val review = project.service<ReviewPersistence>().state ?: return
        val presentation = e.presentation
        presentation.isEnabled = review.isOpened()
    }

    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        val review = project.service<ReviewPersistence>().state ?: return
        val containingFile = e.getData(LangDataKeys.PSI_FILE)?.originalFile ?: return
        val updatedComment = comment.clone()
        val editCommentDialog =
            EditCommentDialog(updatedComment)
        if (editCommentDialog.showAndGet()) {
            updatedComment.details = editCommentDialog.getDetails()
            review.replaceComment(comment, updatedComment)
            DaemonCodeAnalyzer.getInstance(project).restart(containingFile)
        }
    }
}
