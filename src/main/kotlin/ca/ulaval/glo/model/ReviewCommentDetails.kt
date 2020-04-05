package ca.ulaval.glo.model

class ReviewCommentDetails() : ValueObject() {
    var label = ""
    var description = ""
    var tags = mutableListOf<CommentTag>()

    constructor(_label: String, _description: String, _tags: List<CommentTag>) : this() {
        label = _label
        description = _description
        tags = _tags.toMutableList()
    }

    fun clone(): ReviewCommentDetails {
        return ReviewCommentDetails(label, description, tags.toMutableList())
    }
}
