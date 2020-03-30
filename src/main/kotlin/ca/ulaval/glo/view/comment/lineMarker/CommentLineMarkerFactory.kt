package ca.ulaval.glo.view.comment.lineMarker

import ca.ulaval.glo.action.EditCommentAction
import ca.ulaval.glo.action.RemoveCommentAction
import ca.ulaval.glo.persistence.review.state.ReviewComment
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
    // TODO: split apart action group creation
    // TODO: split apart tooltip provider
    fun create(element: PsiElement, reviewComment: ReviewComment): CommentLineMarker {
        val actionGroup = DefaultActionGroup()
        actionGroup.add(
            LineMarkerActionWrapper(
                element,
                EditCommentAction(reviewComment)
            )
        )
        actionGroup.add(Separator())
        actionGroup.add(
            LineMarkerActionWrapper(
                element,
                RemoveCommentAction(reviewComment)
            )
        )
        actionGroup.add(Separator())
        val tooltipProvider =
            Function { _: PsiElement ->
                val tooltip = StringBuilder()
                tooltip.append("Code Review Comment")
                val reviewCommentDetails = reviewComment.details
                val description = reviewCommentDetails.description
                if (description.isNotEmpty()) {
                    tooltip.append("\n\nDescription:\n")
                    tooltip.append(prepend(wrap(description, 60), "\t"))
                }
                val tags = reviewCommentDetails.tags
                if (tags.isNotEmpty()) {
                    tooltip.append("\n\nTags:\n")
                    tooltip.append("\t" + tags.joinToString(", "))
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
