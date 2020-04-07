package ca.ulaval.glo.model.review

import ca.ulaval.glo.model.review.comment.ReviewFileComment
import ca.ulaval.glo.model.review.comment.ReviewGeneralComment
import ca.ulaval.glo.report.ReportGenerator
import com.intellij.openapi.project.Project

class Review {
    var status: ReviewStatus? = null
    var projectBasePath: String? = null
    var details = ReviewDetails()
    var generalComments = mutableListOf<ReviewGeneralComment>()
    var filesComments = mutableMapOf<String, MutableList<ReviewFileComment>>()

    fun isCreated(): Boolean {
        return status != null
    }

    fun isOpened(): Boolean {
        return isCreated() && status == ReviewStatus.OPENED
    }

    fun isClosed(): Boolean {
        return isCreated() && status == ReviewStatus.CLOSED
    }

    fun isNotEmpty(): Boolean {
        return isCreated() && filesComments.isNotEmpty()
    }

    fun create(project: Project, reviewDetails: ReviewDetails) {
        status = ReviewStatus.OPENED
        projectBasePath = project.basePath
        details = reviewDetails
    }

    fun close() {
        status = ReviewStatus.CLOSED
    }

    fun reopen() {
        status = ReviewStatus.OPENED
    }

    fun delete() {
        status = null
        details.reset()
        filesComments = mutableMapOf()
    }

    fun addComment(fileCommentToAdd: ReviewFileComment) {
        val filePath = fileCommentToAdd.filePath
        val updatedComments = getFileReviewComments(filePath)
        updatedComments.add(fileCommentToAdd)
        filesComments[filePath] = updatedComments
    }

    fun removeComment(fileCommentToRemove: ReviewFileComment) {
        val filePath = fileCommentToRemove.filePath
        val updatedComments = getFileReviewComments(filePath)
        val commentToRemoveHashCode = fileCommentToRemove.hashCode()
        updatedComments.removeIf(fun(fileComment: ReviewFileComment): Boolean {
            return fileComment.hashCode() == commentToRemoveHashCode
        })
        filesComments[filePath] = updatedComments
    }

    fun replaceComment(oldFileComment: ReviewFileComment, newFileComment: ReviewFileComment) {
        removeComment(oldFileComment)
        addComment(newFileComment)
    }

    private fun getFileReviewComments(filePath: String): MutableList<ReviewFileComment> {
        return filesComments.getOrDefault(filePath, mutableListOf())
    }

    fun generateReport(outputPath: String) {
        ReportGenerator().generate(this, outputPath)
    }
}
