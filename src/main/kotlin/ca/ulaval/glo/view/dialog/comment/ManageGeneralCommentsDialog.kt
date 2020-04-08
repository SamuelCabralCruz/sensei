package ca.ulaval.glo.view.dialog.comment

import ca.ulaval.glo.model.review.Review
import ca.ulaval.glo.model.review.comment.ReviewGeneralComment
import ca.ulaval.glo.view.dialog.Label
import ca.ulaval.glo.view.dialog.Panel
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.openapi.ui.Messages
import com.intellij.ui.HideableTitledPanel
import com.intellij.ui.components.JBScrollPane
import com.intellij.ui.components.JBTextArea
import java.awt.Dimension
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.Action
import javax.swing.JButton
import javax.swing.JComponent
import javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
import javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED

class ManageGeneralCommentsDialog(private val review: Review) :
    DialogWrapper(true) {
    init {
        init()
        title = "Manage General Comments"
        setSize(700, 400)
    }

    override fun createActions(): Array<Action> {
        return arrayOf(myOKAction)
    }

    override fun createCenterPanel(): JComponent? {
        val panel = Panel()
        val gb = panel.gridBag
        review.generalComments.sortBy { it.details.label }
        review.generalComments.forEach(fun(comment) {
            val titledPanel = HideableTitledPanel(comment.details.label, null, false)
            val subPanel = generateGeneralCommentComponent(review, comment, titledPanel, panel)
            titledPanel.setContentComponent(subPanel)
            panel.add(titledPanel, gb.nextLine().next())
        })
        val group = JBScrollPane(panel, VERTICAL_SCROLLBAR_AS_NEEDED, HORIZONTAL_SCROLLBAR_NEVER)
        group.minimumSize = Dimension(700, 400)
        val mainPanel = Panel()
        val mainGb = mainPanel.gridBag
        mainPanel.add(Label("General Comments"), mainGb.nextLine().next())
        mainPanel.add(group, mainGb.nextLine().next())
        mainPanel.minimumSize = Dimension(700, 400)
        mainPanel.maximumSize = Dimension(700, 400)
        return mainPanel
    }

    private class EditCommentActionListener(
        private val review: Review,
        private val comment: ReviewGeneralComment,
        private val titledPanel: HideableTitledPanel,
        private val panel: Panel
    ) :
        ActionListener {
        override fun actionPerformed(p0: ActionEvent?) {
            val updatedComment = comment.clone()
            val editCommentDialog =
                EditCommentDialog("Edit General Comment", updatedComment)
            if (editCommentDialog.showAndGet()) {
                updatedComment.details = editCommentDialog.getDetails()
                review.replaceGeneralComment(comment, updatedComment)

                val parent = titledPanel.parent
                val newTitledPanel = HideableTitledPanel(updatedComment.details.label, null, true)
                newTitledPanel.setContentComponent(
                    generateGeneralCommentComponent(
                        review,
                        updatedComment,
                        newTitledPanel,
                        panel
                    )
                )
                newTitledPanel.invalidate()
                parent.remove(titledPanel)
                parent.add(newTitledPanel, panel.gridBag.nextLine().next())
                parent.invalidate()
                parent.repaint()
                parent.parent.invalidate()
                parent.parent.repaint()
            }
        }
    }

    private class RemoveCommentActionListener(
        private val review: Review,
        private val comment: ReviewGeneralComment,
        private val titledPanel: HideableTitledPanel
    ) : ActionListener {
        override fun actionPerformed(p0: ActionEvent?) {
            if (Messages.showOkCancelDialog(
                    "Are you sure you want to delete this comment?",
                    "Remove Comment",
                    "Ok",
                    "Cancel",
                    Messages.getWarningIcon()
                ) == Messages.OK
            ) {
                review.removeGeneralComment(comment)
                val parent = titledPanel.parent
                parent.remove(titledPanel)
                parent.invalidate()
                parent.repaint()
            }
        }
    }

    companion object {
        private fun generateGeneralCommentComponent(
            review: Review,
            comment: ReviewGeneralComment,
            titledPanel: HideableTitledPanel,
            panel: Panel
        ): Panel {
            val subPanel = Panel(3)
            val subPanelGb = subPanel.gridBag
            val descriptionField = JBTextArea(comment.details.description)
            descriptionField.isEditable = false
            descriptionField.lineWrap = true
            subPanel.add(descriptionField, subPanelGb.nextLine().next().coverLine())
            subPanelGb.nextLine().next()
            val editButton = JButton("Edit")
            editButton.addActionListener(EditCommentActionListener(review, comment, titledPanel, panel))
            subPanel.add(editButton, subPanelGb.next())
            val removeButton = JButton("Remove")
            removeButton.addActionListener(RemoveCommentActionListener(review, comment, titledPanel))
            subPanel.add(removeButton, subPanelGb.next())
            return subPanel
        }
    }
}
