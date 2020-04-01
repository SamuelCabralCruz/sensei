package ca.ulaval.glo.model

class ReviewCommentDetails() {
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

    override fun toString(): String {
        return label
    }

    override fun equals(other: Any?): Boolean {
        other ?: return false
        if (other !is ReviewCommentDetails) return false
        return label == other.label && description == other.description && tags == other.tags
    }
}
