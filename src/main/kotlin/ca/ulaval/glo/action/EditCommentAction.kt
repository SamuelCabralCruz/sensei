package ca.ulaval.glo.action

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.ui.Messages
import com.intellij.util.PlatformIcons

class EditCommentAction : AnAction("Edit comment", "", PlatformIcons.EDIT) {
    override fun actionPerformed(e: AnActionEvent) {
        Messages.showMessageDialog(e.project, "comment edited", "sensei", Messages.getInformationIcon())
    }
}
