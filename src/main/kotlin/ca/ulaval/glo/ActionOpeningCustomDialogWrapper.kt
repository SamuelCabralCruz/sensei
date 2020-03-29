package ca.ulaval.glo

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.ui.Messages

class ActionOpeningCustomDialogWrapper : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        if (CustomDialogWrapper().showAndGet()) {
            Messages.showMessageDialog(
                "User clicked ok",
                "CustomDialogWrapperActionCallback",
                Messages.getInformationIcon()
            )
        }
    }
}
