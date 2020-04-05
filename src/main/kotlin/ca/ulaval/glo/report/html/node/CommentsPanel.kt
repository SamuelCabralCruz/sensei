package ca.ulaval.glo.report.html.node

import ca.ulaval.glo.model.Review
import ca.ulaval.glo.model.ReviewComment
import ca.ulaval.glo.report.file.comparator.GroupSourceAndTestFilesPathComparator
import ca.ulaval.glo.report.html.HtmlBuffer

class CommentsPanel(private val review: Review) : HtmlNode() {
    override fun openTag(buffer: HtmlBuffer) {
        buffer.append("<div class='comments-panel'>")
        buffer.increaseIndent()
        appendTitle(buffer)
        appendFilesComments(buffer)
    }

    private fun appendTitle(buffer: HtmlBuffer) {
        buffer.append("<h1>")
        buffer.increaseIndent()
        buffer.append(review.details.evaluationName!!)
        buffer.decreaseIndent()
        buffer.append("</h1>")
    }

    private fun appendFilesComments(buffer: HtmlBuffer) {
        buffer.append("<div class='file-comments'>")
        buffer.increaseIndent()
        review.comments.toSortedMap(GroupSourceAndTestFilesPathComparator()).forEach(fun(fileName, fileComments) {
            appendFileComments(buffer, fileName, fileComments)
        })
        buffer.decreaseIndent()
        buffer.append("</div>")
    }

    private fun appendFileComments(
        buffer: HtmlBuffer,
        fileName: String,
        fileComments: MutableList<ReviewComment>
    ) {
        buffer.append("<details key='$fileName' class='file-comments'>")
        buffer.increaseIndent()
        buffer.append("<summary class='file-comments-summary'>$fileName</summary>")
        buffer.append("<ul class='file-comments-details'>")
        buffer.increaseIndent()
        fileComments.sortBy { it.startingLine }
        fileComments.forEachIndexed(fun(index, fileComment) {
            appendFileComment(buffer, index, fileComment)
        })
        buffer.decreaseIndent()
        buffer.append("</ul>")
        buffer.decreaseIndent()
        buffer.append("</details>")
    }

    private fun appendFileComment(
        buffer: HtmlBuffer,
        index: Int,
        fileComment: ReviewComment
    ) {
        buffer.append("<li key='${fileComment.hashCode()}-$index'>")
        buffer.increaseIndent()
        buffer.append("<div class='file-comment'>")
        buffer.increaseIndent()
        buffer.append("<p>Line ${fileComment.startingLine} - ${fileComment.details.label}</p>")
        fileComment.details.tags.forEach(fun(tag) {
            val tagClassName = tag.value.toLowerCase().split(" ").joinToString("-")
            buffer.append("<span class='badge $tagClassName'>${tag.value}</span>")
        })
        buffer.append("<p>${fileComment.details.description}</p>")
        buffer.decreaseIndent()
        buffer.append("</div>")
        buffer.decreaseIndent()
        buffer.append("</li>")
    }

    override fun closeTag(buffer: HtmlBuffer) {
        buffer.decreaseIndent()
        buffer.append("</div>")
    }
}
