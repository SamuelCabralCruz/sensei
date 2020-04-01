package ca.ulaval.glo.model

class Review {
    var comments = mutableMapOf<String, MutableList<ReviewComment>>()

    fun getFileReviewComments(filePath: String): MutableList<ReviewComment> {
        return comments.getOrDefault(filePath, mutableListOf())
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
}
