package ca.ulaval.glo.report.html.node

import ca.ulaval.glo.model.Review
import ca.ulaval.glo.report.file.comparator.GroupSourceAndTestFilesPathComparator
import ca.ulaval.glo.report.html.HtmlBuffer

class CommentsPanel(private val review: Review) : HtmlNode() {
    override fun openTag(buffer: HtmlBuffer) {
        buffer.append("<div class='comments-panel'>")
        buffer.increaseIndent()
        appendTitle(buffer)
        appendComments(buffer)
    }

    private fun appendTitle(buffer: HtmlBuffer) {
        buffer.append("<h1>")
        buffer.increaseIndent()
        buffer.append(review.details.evaluationName!!)
        buffer.decreaseIndent()
        buffer.append("</h1>")
    }

    private fun appendComments(buffer: HtmlBuffer) {
        buffer.append("<div class='file-comments'>")
        buffer.increaseIndent()
        review.comments.toSortedMap(GroupSourceAndTestFilesPathComparator()).forEach(fun(fileName, fileComments) {
            buffer.append("<details class='comment'>")
            buffer.increaseIndent()
            buffer.append("<summary class='comment-summary'>$fileName</summary>")
            buffer.append("<ul>")
            buffer.increaseIndent()
            fileComments.sortBy { it.startingLine }
            fileComments.forEach(fun(fileComment) {
                buffer.append("<li>")
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
            })
            buffer.decreaseIndent()
            buffer.append("</ul>")
            buffer.decreaseIndent()
            buffer.append("</details>")
        })
        buffer.decreaseIndent()
        buffer.append("</div>")
    }

    override fun closeTag(buffer: HtmlBuffer) {
        buffer.decreaseIndent()
        buffer.append("</div>")
    }
}
