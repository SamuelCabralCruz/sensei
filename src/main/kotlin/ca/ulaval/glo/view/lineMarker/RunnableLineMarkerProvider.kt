package ca.ulaval.glo.view.lineMarker

import ca.ulaval.glo.persistence.ReviewPersistence
import ca.ulaval.glo.model.ReviewComment
import com.intellij.codeInsight.daemon.LineMarkerInfo
import com.intellij.codeInsight.daemon.LineMarkerProvider
import com.intellij.openapi.components.service
import com.intellij.openapi.util.text.StringUtil
import com.intellij.psi.PsiElement

class RunnableLineMarkerProvider : LineMarkerProvider {
    override fun getLineMarkerInfo(element: PsiElement): LineMarkerInfo<*>? {
        return null
    }

    override fun collectSlowLineMarkers(
        elements: MutableList<PsiElement>,
        result: MutableCollection<LineMarkerInfo<PsiElement>>
    ) {
        val project = elements[0].project
        val reviewComments = project.service<ReviewPersistence>().state?.comments ?: return
        val projectBasePath = project.basePath ?: return
        val containingFile = elements[0].containingFile
        val containingFileText = containingFile.text
        val virtualFilePath = containingFile.virtualFile.path
        val currentFileRelativePath = virtualFilePath.replace(projectBasePath, "")
        val reviewCommentsForCurrentFile = reviewComments[currentFileRelativePath] ?: return
        val reviewCommentsForCurrentFileByLine = mutableMapOf<Int, MutableList<ReviewComment>>()
        reviewCommentsForCurrentFile.forEach(fun(reviewComment) {
            val lineReviewComments = reviewCommentsForCurrentFileByLine[reviewComment.highlight.first] ?: mutableListOf()
            lineReviewComments.add(reviewComment)
            reviewCommentsForCurrentFileByLine[reviewComment.highlight.first] = lineReviewComments
        })
        elements.forEach(fun(element) {
            val elementLine = StringUtil.offsetToLineNumber(containingFileText, element.textRange.startOffset)
            val lineReviewComments = reviewCommentsForCurrentFileByLine[elementLine]
            lineReviewComments?.forEach(fun(reviewComment) {
                result.add(CommentLineMarkerFactory().create(element, reviewComment))
                reviewCommentsForCurrentFileByLine.remove(elementLine)
            })
        })
    }
}
