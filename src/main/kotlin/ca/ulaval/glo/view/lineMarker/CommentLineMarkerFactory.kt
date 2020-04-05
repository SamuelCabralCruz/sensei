package ca.ulaval.glo.view.lineMarker

import ca.ulaval.glo.action.EditCommentAction
import ca.ulaval.glo.action.RemoveCommentAction
import ca.ulaval.glo.model.ReviewComment
import ca.ulaval.glo.persistence.ReviewPersistence
import ca.ulaval.glo.util.prepend
import ca.ulaval.glo.util.wrap
import com.intellij.execution.lineMarker.LineMarkerActionWrapper
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.DefaultActionGroup
import com.intellij.openapi.actionSystem.Separator
import com.intellij.openapi.components.service
import com.intellij.psi.PsiElement
import com.intellij.util.Function

class CommentLineMarkerFactory {
    fun create(element: PsiElement, reviewComment: ReviewComment): CommentLineMarker {
        return CommentLineMarker(
            element,
            createCommentLineMarkerTooltipProvider(reviewComment),
            CommentLineMarkerActionGroup(element, reviewComment)
        )
    }

    private fun createCommentLineMarkerTooltipProvider(reviewComment: ReviewComment): Function<PsiElement, String> {
        return Function {
            val tooltip = StringBuilder()
            tooltip.append("Code Review Comment")
            val reviewCommentDetails = reviewComment.details
            tooltip.append("\n\nLabel:\n")
            tooltip.append(reviewCommentDetails.label)
            val description = reviewCommentDetails.description
            if (description.isNotBlank()) {
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

    private class CommentLineMarkerActionGroup(
        private val element: PsiElement,
        private val reviewComment: ReviewComment
    ) :
        DefaultActionGroup() {
        init {
            addAllActions()
        }

        private fun addAllActions() {
            add(
                LineMarkerActionWrapper(
                    element,
                    EditCommentAction(reviewComment)
                )
            )
            add(Separator())
            add(
                LineMarkerActionWrapper(
                    element,
                    RemoveCommentAction(reviewComment)
                )
            )
            add(Separator())
        }

        override fun update(e: AnActionEvent) {
            val project = e.project ?: return
            val review = project.service<ReviewPersistence>().state ?: return
            if (!review.isOpened()) {
                removeAll()
            } else {
                if (childActionsOrStubs.isEmpty()) addAllActions()
            }
        }
    }
}
