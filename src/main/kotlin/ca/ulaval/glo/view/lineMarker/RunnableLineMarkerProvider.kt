package ca.ulaval.glo.view.lineMarker

import ca.ulaval.glo.model.review.comment.ReviewFileComment
import ca.ulaval.glo.persistence.ReviewPersistence
import com.intellij.codeInsight.daemon.LineMarkerInfo
import com.intellij.codeInsight.daemon.LineMarkerProvider
import com.intellij.openapi.components.service
import com.intellij.openapi.util.text.StringUtil
import com.intellij.psi.PsiElement

class RunnableLineMarkerProvider : LineMarkerProvider {
    override fun getLineMarkerInfo(element: PsiElement): LineMarkerInfo<*>? {
        return null
    }

    override fun collectSlowLineMarkers(elements: MutableList<out PsiElement>, result: MutableCollection<in LineMarkerInfo<*>>) {
        if (elements.isEmpty()) return
        val project = elements[0].project
        val reviewComments = project.service<ReviewPersistence>().state?.filesComments ?: return
        val projectBasePath = project.basePath ?: return
        val containingFile = elements[0].containingFile
        val containingFileText = containingFile.text
        val virtualFilePath = containingFile.virtualFile.path
        val currentFileRelativePath = virtualFilePath.replace(projectBasePath, "")
        val reviewCommentsForCurrentFile = reviewComments[currentFileRelativePath] ?: return
        val reviewCommentsForCurrentFileByLine = mutableMapOf<Int, MutableList<ReviewFileComment>>()
        reviewCommentsForCurrentFile.forEach(fun(reviewComment) {
            val lineReviewComments =
                reviewCommentsForCurrentFileByLine[reviewComment.highlightStartingLine] ?: mutableListOf()
            lineReviewComments.add(reviewComment)
            reviewCommentsForCurrentFileByLine[reviewComment.highlightStartingLine] = lineReviewComments
        })
        elements.forEach(fun(element) {
            val elementLine = StringUtil.offsetToLineNumber(containingFileText, element.textRange.startOffset) + 1
            val lineReviewComments = reviewCommentsForCurrentFileByLine[elementLine]
            lineReviewComments?.forEach(fun(reviewComment) {
                result.add(CommentLineMarkerFactory().create(element, reviewComment))
                reviewCommentsForCurrentFileByLine.remove(elementLine)
            })
        })
        if (reviewCommentsForCurrentFileByLine.isNotEmpty()) {
            reviewCommentsForCurrentFileByLine.values.flatten().forEach(fun(reviewComment) {
                val firstElement = elements.first()
                result.add(CommentLineMarkerFactory().create(firstElement, reviewComment))
            })
        }
    }
}
