package ca.ulaval.glo.persistence.review.state

enum class CommentTag(private val key: String, private val short: Char) {
    ARCHITECTURE("Architecture", 'A'),
    CLEAN_CODE("Clean Code", 'C'),
    IMPORTANT("Important", 'I'),
    QUESTION("Question", 'Q');

    fun getKey(): String {
        return key
    }
}
