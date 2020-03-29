package ca.ulaval.glo

import ca.ulaval.glo.persistence.ReviewPersistence
import ca.ulaval.glo.state.ReviewComment
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
        return if (isElementWithComment(element)) {
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

    private fun isElementWithComment(element: PsiElement): Boolean {
        if (element.children.isNotEmpty()) return false
        val review = ReviewPersistence.getInstance().state
        if (review != null) {
            val projectBasePath = element.project.basePath
            val projectFilePath = element.containingFile.virtualFile.path
            val projectFileRelativePath = projectFilePath.replace(projectBasePath!!, "")
            val fileComments = review.comments[projectFileRelativePath]
            if (fileComments != null) {
                return fileComments.any(fun(x: ReviewComment): Boolean {
                    val psiElementAtOffset =
                        element.containingFile.findElementAt(x.startOffset)?.originalElement
                    return element.isEquivalentTo(psiElementAtOffset)
                })
            }
        }
        return false
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

