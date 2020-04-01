package ca.ulaval.glo.model

class ReviewComment() {
    var filePath = ""
    var startingLine = 0
    var codeSnippet = ""
    var details = ReviewCommentDetails()

    constructor(_filePath: String, _startingLine: Int, _codeSnippet: String, _details: ReviewCommentDetails) : this() {
        filePath = _filePath
        startingLine = _startingLine
        codeSnippet = _codeSnippet
        details = _details
    }

    fun clone(): ReviewComment {
        return ReviewComment(filePath, startingLine, codeSnippet, details.clone())
    }
}
