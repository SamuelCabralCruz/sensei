package ca.ulaval.glo.view.lineMarker

import ca.ulaval.glo.action.EditCommentAction
import ca.ulaval.glo.action.RemoveCommentAction
import ca.ulaval.glo.persistence.review.state.ReviewCommentDetails
import ca.ulaval.glo.util.prepend
import ca.ulaval.glo.util.wrap
import com.intellij.execution.lineMarker.LineMarkerActionWrapper
import com.intellij.openapi.actionSystem.DefaultActionGroup
import com.intellij.openapi.actionSystem.Separator
import com.intellij.psi.PsiElement
import com.intellij.util.Function

class CommentLineMarkerFactory {
    // TODO: clean up
    // TODO: edit comment action
    // TODO: remove comment action
    fun create(element: PsiElement, reviewCommentDetails: ReviewCommentDetails): CommentLineMarker {
        val actionGroup = DefaultActionGroup()
        actionGroup.add(
            LineMarkerActionWrapper(
                element,
                EditCommentAction()
            )
        )
        actionGroup.add(Separator())
        actionGroup.add(
            LineMarkerActionWrapper(
                element,
                RemoveCommentAction()
            )
        )
        actionGroup.add(Separator())
        val tooltipProvider =
            Function { _: PsiElement ->
                val tooltip = StringBuilder()
                tooltip.append("Code Review Comment")
                if (reviewCommentDetails.description.isNotEmpty()) {
                    tooltip.append("\n\nDescription:\n")
                    tooltip.append(prepend(wrap(reviewCommentDetails.description, 60), "\t"))
                }
                if (reviewCommentDetails.tags.isNotEmpty()) {
                    tooltip.append("\n\nTags:\n")
                    tooltip.append("\t" + reviewCommentDetails.tags.joinToString(", "))
                }
                if (tooltip.isEmpty()) "" else tooltip.toString()
            }
        return CommentLineMarker(
            element,
            tooltipProvider,
            actionGroup
        )
    }
}
