package ca.ulaval.glo.view.lineMarker

import com.intellij.codeInsight.daemon.LineMarkerInfo
import com.intellij.openapi.actionSystem.ActionGroup
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.DefaultActionGroup
import com.intellij.openapi.editor.markup.GutterIconRenderer
import com.intellij.openapi.editor.markup.MarkupEditorFilter
import com.intellij.openapi.editor.markup.MarkupEditorFilterFactory
import com.intellij.psi.PsiElement
import com.intellij.util.Function
import javax.swing.Icon

class CommentLineMarker(
    element: PsiElement,
    icon: Icon?,
    tooltipProvider: Function<PsiElement, String?>,
    private val myActionGroup: DefaultActionGroup
) :
    LineMarkerInfo<PsiElement>(
        element,
        element.textRange,
        icon,
        tooltipProvider,
        null,
        GutterIconRenderer.Alignment.CENTER
    ) {
    override fun createGutterRenderer(): GutterIconRenderer? {
        return object : LineMarkerGutterIconRenderer<PsiElement>(this) {
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
