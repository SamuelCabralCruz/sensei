package ca.ulaval.glo

import com.intellij.codeInsight.daemon.LineMarkerInfo
import com.intellij.codeInsight.daemon.LineMarkerProvider
import com.intellij.execution.lineMarker.LineMarkerActionWrapper
import com.intellij.openapi.actionSystem.*
import com.intellij.openapi.editor.markup.GutterIconRenderer
import com.intellij.openapi.editor.markup.MarkupEditorFilter
import com.intellij.openapi.editor.markup.MarkupEditorFilterFactory
import com.intellij.openapi.ui.Messages
import com.intellij.psi.PsiElement
import com.intellij.util.Function
import com.intellij.util.PlatformIcons.FILE_ICON
import com.intellij.util.PlatformIcons.JAR_ICON
import javax.swing.Icon

class RunnableLineMarkerProvider : LineMarkerProvider {
    override fun getLineMarkerInfo(element: PsiElement): LineMarkerInfo<*>? {
        return if (element.textRange.startOffset == 118) {
            val actionGroup = DefaultActionGroup()
            actionGroup.add(LineMarkerActionWrapper(element, TestAction()))
            actionGroup.add(Separator())
            val tooltipProvider =
                Function { _: PsiElement? ->
                    val tooltip = StringBuilder()
                    tooltip.append("test action")
                    if (tooltip.isEmpty()) null else tooltip.toString()
                }
            RunLineMarkerInfo(element, FILE_ICON, tooltipProvider, actionGroup)
        } else {
            null
        }
    }

    private class TestAction : AnAction("test action name", "test action description", JAR_ICON) {
        override fun actionPerformed(e: AnActionEvent) {
            Messages.showMessageDialog(e.project, "it works", "sensei", Messages.getInformationIcon())
        }
    }

    private class RunLineMarkerInfo(
        element: PsiElement,
        icon: Icon?,
        tooltipProvider: Function<PsiElement?, String?>?,
        private val myActionGroup: DefaultActionGroup
    ) :
        LineMarkerInfo<PsiElement?>(
            element,
            element.textRange,
            icon,
            tooltipProvider,
            null,
            GutterIconRenderer.Alignment.CENTER
        ) {
        override fun createGutterRenderer(): GutterIconRenderer? {
            return object : LineMarkerGutterIconRenderer<PsiElement?>(this) {
                override fun getClickAction(): AnAction? {
                    return null
                }

                override fun isNavigateAction(): Boolean {
                    return true
                }

                override fun getPopupMenuActions(): ActionGroup? {
                    return myActionGroup
                }
            }
        }

        override fun getEditorFilter(): MarkupEditorFilter {
            return MarkupEditorFilterFactory.createIsNotDiffFilter()
        }
    }
}

