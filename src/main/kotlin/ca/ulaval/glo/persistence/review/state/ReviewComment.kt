package ca.ulaval.glo.persistence.review.state

class ReviewComment {
    var filePath = ""
    var startingLine = 0
    var codeSnippet = ""
    var details = ReviewCommentDetails()
}
