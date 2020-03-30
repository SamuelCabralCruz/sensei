package ca.ulaval.glo.tutorial

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.ui.Messages

class ActionOpeningCustomDialogWrapper : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val customDialogWrapper = CustomDialogWrapper()
        if (customDialogWrapper.showAndGet()) {
            Messages.showMessageDialog(
                "User clicked ok",
                "CustomDialogWrapperActionCallback",
                Messages.getInformationIcon()
            )
        }
    }
}
