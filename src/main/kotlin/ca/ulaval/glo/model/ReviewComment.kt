package ca.ulaval.glo.model

class ReviewComment() {
    var filePath = ""
    var startingLine = 0
    var highlight = IntRange(0, 0)
    var codeSnippet = ""
    var details = ReviewCommentDetails()

    constructor(
        _filePath: String,
        _startingLine: Int,
        _highlight: IntRange,
        _codeSnippet: String,
        _details: ReviewCommentDetails
    ) : this() {
        filePath = _filePath
        startingLine = _startingLine
        highlight = _highlight
        codeSnippet = _codeSnippet
        details = _details
    }

    fun clone(): ReviewComment {
        return ReviewComment(filePath, startingLine, highlight, codeSnippet, details.clone())
    }
}
