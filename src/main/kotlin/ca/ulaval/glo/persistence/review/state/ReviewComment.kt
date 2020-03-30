package ca.ulaval.glo.persistence.review.state

class ReviewComment {
    var filePath = ""
    var startingLine = 0
    var codeSnippet = ""
    var details = ReviewCommentDetails()

    fun clone(): ReviewComment {
        val copied = ReviewComment()
        copied.filePath = filePath
        copied.startingLine = startingLine
        copied.codeSnippet = codeSnippet
        copied.details = details.clone()
        return copied
    }
}
