package ca.ulaval.glo.model

class ReviewComment() : ValueObject() {
    var filePath = ""
    var startingLine = 0
    var highlightStartingLine = 0
    var highlightEndingLine = 0
    var codeSnippet = ""
    var details = ReviewCommentDetails()

    constructor(
        _filePath: String,
        _startingLine: Int,
        _highlightStartingLine: Int,
        _highlightEndingLine: Int,
        _codeSnippet: String,
        _details: ReviewCommentDetails
    ) : this() {
        filePath = _filePath
        startingLine = _startingLine
        highlightStartingLine = _highlightStartingLine
        highlightEndingLine = _highlightEndingLine
        codeSnippet = _codeSnippet
        details = _details
    }

    fun clone(): ReviewComment {
        return ReviewComment(
            filePath,
            startingLine,
            highlightStartingLine,
            highlightEndingLine,
            codeSnippet,
            details.clone()
        )
    }
}
