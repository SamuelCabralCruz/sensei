package ca.ulaval.glo.view.lineMarker

import com.intellij.execution.lineMarker.LineMarkerActionWrapper
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.DefaultActionGroup
import com.intellij.openapi.actionSystem.Separator
import com.intellij.openapi.ui.Messages
import com.intellij.psi.PsiElement
import com.intellij.util.Function
import com.intellij.util.PlatformIcons

class CommentLineMarkerFactory {
    // TODO: clean up
    // TODO: edit comment action
    // TODO: remove comment action
    fun create(element: PsiElement): CommentLineMarker {
        val actionGroup = DefaultActionGroup()
        actionGroup.add(
            LineMarkerActionWrapper(
                element,
                TestAction()
            )
        )
        actionGroup.add(Separator())
        val tooltipProvider =
            Function { _: PsiElement ->
                val tooltip = StringBuilder()
                tooltip.append("test action")
                if (tooltip.isEmpty()) null else tooltip.toString()
            }
        return CommentLineMarker(
            element,
            PlatformIcons.FILE_ICON,
            tooltipProvider,
            actionGroup
        )
    }

    private class TestAction : AnAction("test action name", "test action description", PlatformIcons.JAR_ICON) {
        override fun actionPerformed(e: AnActionEvent) {
            Messages.showMessageDialog(e.project, "it works", "sensei", Messages.getInformationIcon())
        }
    }
}
