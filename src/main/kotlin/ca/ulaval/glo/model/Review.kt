package ca.ulaval.glo.model

import ca.ulaval.glo.model.report.ReportGenerator
import com.intellij.openapi.project.Project

class Review {
    var status: ReviewStatus? = null
    var projectBasePath: String? = null
    var details = ReviewDetails()
    var comments = mutableMapOf<String, MutableList<ReviewComment>>()

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
        return isCreated() && comments.isNotEmpty()
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
        comments = mutableMapOf()
    }

    fun addComment(commentToAdd: ReviewComment) {
        val filePath = commentToAdd.filePath
        val updatedComments = getFileReviewComments(filePath)
        updatedComments.add(commentToAdd)
        comments[filePath] = updatedComments
    }

    fun removeComment(commentToRemove: ReviewComment) {
        val filePath = commentToRemove.filePath
        val updatedComments = getFileReviewComments(filePath)
        updatedComments.removeIf(fun(comment: ReviewComment): Boolean {
            return comment.hashCode() == commentToRemove.hashCode()
        })
        comments[filePath] = updatedComments
    }

    fun replaceComment(oldComment: ReviewComment, newComment: ReviewComment) {
        removeComment(oldComment)
        addComment(newComment)
    }

    private fun getFileReviewComments(filePath: String): MutableList<ReviewComment> {
        return comments.getOrDefault(filePath, mutableListOf())
    }

    fun generateReport(outputPath: String) {
        ReportGenerator().generate(this, outputPath)
    }
}