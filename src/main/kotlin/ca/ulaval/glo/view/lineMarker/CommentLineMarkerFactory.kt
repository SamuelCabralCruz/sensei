package ca.ulaval.glo.view.lineMarker

import ca.ulaval.glo.action.EditCommentAction
import ca.ulaval.glo.action.RemoveCommentAction
import ca.ulaval.glo.model.ReviewComment
import ca.ulaval.glo.util.prepend
import ca.ulaval.glo.util.wrap
import com.intellij.execution.lineMarker.LineMarkerActionWrapper
import com.intellij.openapi.actionSystem.DefaultActionGroup
import com.intellij.openapi.actionSystem.Separator
import com.intellij.psi.PsiElement
import com.intellij.util.Function

class CommentLineMarkerFactory {
    fun create(element: PsiElement, reviewComment: ReviewComment): CommentLineMarker {
        return CommentLineMarker(
            element,
            createCommentLineMarkerTooltipProvider(reviewComment),
            createCommentLineMarkerActionGroup(element, reviewComment)
        )
    }

    private fun createCommentLineMarkerTooltipProvider(reviewComment: ReviewComment): Function<PsiElement, String> {
        return Function {
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
    }

    private fun createCommentLineMarkerActionGroup(
        element: PsiElement,
        reviewComment: ReviewComment
    ): DefaultActionGroup {
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
        return actionGroup
    }
}
