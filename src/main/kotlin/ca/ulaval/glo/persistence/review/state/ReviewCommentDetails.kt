package ca.ulaval.glo.persistence.review.state

class ReviewCommentDetails {
    var description = ""
    var tags = mutableListOf<CommentTag>()

    fun clone(): ReviewCommentDetails {
        val copied = ReviewCommentDetails()
        copied.description = description
        copied.tags = tags.toMutableList()
        return copied
    }
}
