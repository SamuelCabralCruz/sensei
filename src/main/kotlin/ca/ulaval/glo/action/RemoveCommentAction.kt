package ca.ulaval.glo.action

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.ui.Messages
import com.intellij.util.PlatformIcons

class RemoveCommentAction : AnAction("Remove comment", "", PlatformIcons.DELETE_ICON) {
    override fun actionPerformed(e: AnActionEvent) {
        Messages.showMessageDialog(e.project, "comment removed", "sensei", Messages.getInformationIcon())
    }
}
