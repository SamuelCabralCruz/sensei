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

    fun hasGeneralComments(): Boolean {
        return isCreated() && generalComments.isNotEmpty()
    }

    fun hasFileComments(): Boolean {
        return isCreated() && filesComments.isNotEmpty()
    }

    fun isNotEmpty(): Boolean {
        return hasGeneralComments() || hasFileComments()
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

    fun addGeneralComment(generalCommentToAdd: ReviewGeneralComment) {
        generalComments.add(generalCommentToAdd)
    }

    fun removeGeneralComment(generalCommentToRemove: ReviewGeneralComment) {
        generalComments.remove(generalCommentToRemove)
    }

    fun replaceGeneralComment(oldGeneralComment: ReviewGeneralComment, newGeneralComment: ReviewGeneralComment) {
        removeGeneralComment(oldGeneralComment)
        addGeneralComment(newGeneralComment)
    }

    fun addFileComment(fileCommentToAdd: ReviewFileComment) {
        val filePath = fileCommentToAdd.filePath
        val updatedComments = getFileComments(filePath)
        updatedComments.add(fileCommentToAdd)
        filesComments[filePath] = updatedComments
    }

    fun removeFileComment(fileCommentToRemove: ReviewFileComment) {
        val filePath = fileCommentToRemove.filePath
        val updatedComments = getFileComments(filePath)
        val commentToRemoveHashCode = fileCommentToRemove.hashCode()
        updatedComments.removeIf(fun(fileComment: ReviewFileComment): Boolean {
            return fileComment.hashCode() == commentToRemoveHashCode
        })
        filesComments[filePath] = updatedComments
    }

    fun replaceFileComment(oldFileComment: ReviewFileComment, newFileComment: ReviewFileComment) {
        removeFileComment(oldFileComment)
        addFileComment(newFileComment)
    }

    private fun getFileComments(filePath: String): MutableList<ReviewFileComment> {
        return filesComments.getOrDefault(filePath, mutableListOf())
    }

    fun generateReport(outputPath: String) {
        ReportGenerator().generate(this, outputPath)
    }
}
