package ca.ulaval.glo.persistence.review.state

enum class CommentTag(private val key: String) {
    ARCHITECTURE("Architecture"),
    CLEAN_CODE("Clean Code"),
    IMPORTANT("Important"),
    QUESTION("Question");

    fun getKey(): String {
        return key
    }
}
