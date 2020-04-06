package ca.ulaval.glo.report.html.node

import ca.ulaval.glo.model.CommentTag
import ca.ulaval.glo.model.Review
import ca.ulaval.glo.model.ReviewComment
import ca.ulaval.glo.report.file.comparator.GroupSourceAndTestFilesPathComparator
import ca.ulaval.glo.report.html.HtmlBuffer

class CommentsPanel(private val review: Review) : HtmlNode() {
    override fun openTag(buffer: HtmlBuffer) {
        buffer.append("<div class='comments-panel'>")
        buffer.increaseIndent()
        appendCommentsPanelHeader(buffer)
        appendFilesComments(buffer)
    }

    private fun appendCommentsPanelHeader(buffer: HtmlBuffer) {
        buffer.append("<div class='comments-panel-header'>")
        buffer.increaseIndent()
        appendCommentsPanelTitle(buffer)
        buffer.decreaseIndent()
        buffer.append("</div>")
    }

    private fun appendCommentsPanelTitle(buffer: HtmlBuffer) {
        buffer.append("<h1 class='comments-panel-title'>")
        buffer.increaseIndent()
        buffer.append(review.details.evaluationName!!)
        buffer.decreaseIndent()
        buffer.append("</h1>")
    }

    private fun appendFilesComments(buffer: HtmlBuffer) {
        buffer.append("<div class='files-comments'>")
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
        buffer.append("<div class='file-comments-details'>")
        buffer.increaseIndent()
        fileComments.sortBy { it.startingLine }
        fileComments.forEachIndexed(fun(index, fileComment) {
            appendFileComment(buffer, index, fileComment)
        })
        buffer.decreaseIndent()
        buffer.append("</div>")
        buffer.decreaseIndent()
        buffer.append("</details>")
    }

    private fun appendFileComment(
        buffer: HtmlBuffer,
        index: Int,
        fileComment: ReviewComment
    ) {
        buffer.append("<div key='${fileComment.hashCode()}-$index' class='file-comment'>")
        buffer.increaseIndent()
        appendFileCommentLogo(buffer)
        appendFileCommentContent(buffer, fileComment)
        buffer.decreaseIndent()
        buffer.append("</div>")
    }

    private fun appendFileCommentLogo(buffer: HtmlBuffer) {
        buffer.append("<img class='file-comment-logo' src='assets/img/lotus.png' type='image/png'/>")
    }

    private fun appendFileCommentContent(
        buffer: HtmlBuffer,
        fileComment: ReviewComment
    ) {
        buffer.append("<div class='file-comment-content'>")
        buffer.increaseIndent()
        appendFileCommentContentHeader(buffer, fileComment)
        appendFileCommentContentBody(buffer, fileComment)
        buffer.decreaseIndent()
        buffer.append("</div>")
    }

    private fun appendFileCommentContentHeader(
        buffer: HtmlBuffer,
        fileComment: ReviewComment
    ) {
        buffer.append("<div class='file-comment-content-header'>")
        buffer.increaseIndent()
        buffer.append("Line ${fileComment.highlight.start} - ${fileComment.details.label}")
        buffer.decreaseIndent()
        buffer.append("</div>")
    }

    private fun appendFileCommentContentBody(
        buffer: HtmlBuffer,
        fileComment: ReviewComment
    ) {
        val tags = fileComment.details.tags
        val description = fileComment.details.description
        if (tags.isEmpty() && description.isBlank()) return
        buffer.append("<div class='file-comment-content-body'>")
        buffer.increaseIndent()
        if (tags.isNotEmpty()) appendFileCommentTags(buffer, tags)
        if (description.isNotBlank()) buffer.append("<div>$description</div>")
        buffer.decreaseIndent()
        buffer.append("</div>")
    }

    private fun appendFileCommentTags(
        buffer: HtmlBuffer,
        tags: MutableList<CommentTag>
    ) {
        buffer.append("<div>")
        buffer.increaseIndent()
        tags.forEach(fun(tag) {
            val tagClassName = tag.value.toLowerCase().split(" ").joinToString("-")
            buffer.append("<span class='badge $tagClassName'>${tag.value}</span>")
        })
        buffer.decreaseIndent()
        buffer.append("</div>")
    }

    override fun closeTag(buffer: HtmlBuffer) {
        buffer.decreaseIndent()
        buffer.append("</div>")
    }
}
