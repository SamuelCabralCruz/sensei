package ca.ulaval.glo.report.html.node

import ca.ulaval.glo.model.review.Review
import ca.ulaval.glo.model.review.comment.CommentTag
import ca.ulaval.glo.model.review.comment.ReviewComment
import ca.ulaval.glo.model.review.comment.ReviewFileComment
import ca.ulaval.glo.model.review.comment.ReviewGeneralComment
import ca.ulaval.glo.report.file.comparator.GroupSourceAndTestFilesPathComparator
import ca.ulaval.glo.report.html.HtmlBuffer
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter.RFC_1123_DATE_TIME

class CommentsPanel(private val review: Review) : HtmlNode() {
    override fun openTag(buffer: HtmlBuffer) {
        buffer.append("<div class='comments-panel'>")
        buffer.increaseIndent()
        appendCommentsPanelHeader(buffer)
        appendGeneralComments(buffer)
        appendFilesComments(buffer)
        appendCopyright(buffer)
    }

    private fun appendCommentsPanelHeader(buffer: HtmlBuffer) {
        buffer.append("<div class='comments-panel-header'>")
        buffer.increaseIndent()
        appendCommentsPanelHeaderTitle(buffer)
        appendCommentsPanelHeaderDetails(buffer)
        buffer.decreaseIndent()
        buffer.append("</div>")
    }

    private fun appendCommentsPanelHeaderTitle(buffer: HtmlBuffer) {
        buffer.append("<div class='comments-panel-header-title'>")
        buffer.increaseIndent()
        buffer.append(review.details.evaluationName!!.capitalize())
        buffer.decreaseIndent()
        buffer.append("</div>")
    }

    private fun appendCommentsPanelHeaderDetails(buffer: HtmlBuffer) {
        buffer.append("<div class='comments-panel-header-details'>")
        buffer.increaseIndent()
        buffer.append("<div class='comments-panel-header-details-reviewer'>${review.details.reviewer!!}</div>")
        val formattedTeamNumber = review.details.teamNumber!!.toString().padStart(2, '0')
        buffer.append("<div class='comments-panel-header-details-team-number'>Team #$formattedTeamNumber</div>")
        buffer.decreaseIndent()
        buffer.append("</div>")
    }

    private fun appendGeneralComments(buffer: HtmlBuffer) {
        buffer.append("<div class='general-comments'>")
        buffer.increaseIndent()
        buffer.append("<div class='general-comments-title'>General Comments</div>")
        buffer.append("<div class='general-comments-content'>")
        buffer.increaseIndent()
        review.generalComments.sortBy { it.details.label }
        review.generalComments.forEachIndexed(fun(index, generalComment) {
            appendGeneralComment(buffer, index, generalComment)
        })
        buffer.decreaseIndent()
        buffer.append("</div>")
        buffer.decreaseIndent()
        buffer.append("</div>")
    }

    private fun appendGeneralComment(buffer: HtmlBuffer, index: Int, generalComment: ReviewGeneralComment) {
        buffer.append("<div key='${generalComment.hashCode()}-$index' class='general-comment'>")
        buffer.increaseIndent()
        appendCommentLogo(buffer)
        appendCommentContent(buffer, generalComment)
        buffer.decreaseIndent()
        buffer.append("</div>")
    }

    private fun appendFilesComments(buffer: HtmlBuffer) {
        buffer.append("<div class='files-comments'>")
        buffer.increaseIndent()
        buffer.append("<div class='files-comments-title'>Specific Comments</div>")
        buffer.append("<div class='files-comments-content'>")
        buffer.increaseIndent()
        review.filesComments.toSortedMap(GroupSourceAndTestFilesPathComparator()).forEach(fun(fileName, fileComments) {
            appendFileComments(buffer, fileName, fileComments)
        })
        buffer.decreaseIndent()
        buffer.append("</div>")
        buffer.decreaseIndent()
        buffer.append("</div>")
    }

    private fun appendFileComments(
        buffer: HtmlBuffer,
        fileName: String,
        fileComments: MutableList<ReviewFileComment>
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
        fileComment: ReviewFileComment
    ) {
        buffer.append("<div key='${fileComment.hashCode()}-$index' class='file-comment'>")
        buffer.increaseIndent()
        appendCommentLogo(buffer)
        appendCommentContent(buffer, fileComment)
        buffer.decreaseIndent()
        buffer.append("</div>")
    }

    private fun appendCommentLogo(buffer: HtmlBuffer) {
        buffer.append("<img class='comment-logo' src='assets/img/lotus.png' type='image/png'/>")
    }

    private fun appendCommentContent(
        buffer: HtmlBuffer,
        comment: ReviewComment
    ) {
        buffer.append("<div class='comment-content'>")
        buffer.increaseIndent()
        appendCommentContentHeader(buffer, comment)
        appendCommentContentBody(buffer, comment)
        buffer.decreaseIndent()
        buffer.append("</div>")
    }

    private fun appendCommentContentHeader(
        buffer: HtmlBuffer,
        comment: ReviewComment
    ) {
        buffer.append("<div class='comment-content-header'>")
        buffer.increaseIndent()
        appendCommentContentLabel(comment, buffer)
        buffer.decreaseIndent()
        buffer.append("</div>")
    }

    private fun appendCommentContentLabel(
        comment: ReviewComment,
        buffer: HtmlBuffer
    ) {
        var contentHeader = ""
        if (comment is ReviewFileComment) {
            contentHeader += "Line ${comment.highlightStartingLine} - "
        }
        contentHeader += comment.details.label
        buffer.append(contentHeader)
    }

    private fun appendCommentContentBody(
        buffer: HtmlBuffer,
        comment: ReviewComment
    ) {
        val tags = comment.details.tags
        val description = comment.details.description
        if (tags.isEmpty() && description.isBlank()) return
        buffer.append("<div class='comment-content-body'>")
        buffer.increaseIndent()
        appendCommentTags(buffer, tags)
        appendCommentDescription(description, buffer)
        buffer.decreaseIndent()
        buffer.append("</div>")
    }

    private fun appendCommentTags(
        buffer: HtmlBuffer,
        tags: MutableList<CommentTag>
    ) {
        if (tags.isEmpty()) return
        buffer.append("<div class='comment-content-body-tags'>")
        buffer.increaseIndent()
        tags.forEach(fun(tag) {
            val tagClassName = tag.value.toLowerCase().split(" ").joinToString("-")
            buffer.append("<span class='badge $tagClassName'>${tag.value}</span>")
        })
        buffer.decreaseIndent()
        buffer.append("</div>")
    }

    private fun appendCommentDescription(description: String, buffer: HtmlBuffer) {
        if (description.isBlank()) return
        buffer.append("<div class='comment-content-body-description'>$description</div>")
    }

    private fun appendCopyright(buffer: HtmlBuffer) {
        buffer.append("<div class='copyright'>")
        buffer.increaseIndent()
        val timestamp = RFC_1123_DATE_TIME.withZone(ZoneOffset.UTC).format(Instant.now())
        buffer.append("Generated with <b>&nbsp;&copy;SenseiCodeReviews&nbsp;</b> on $timestamp")
        buffer.decreaseIndent()
        buffer.append("</div>")
    }

    override fun closeTag(buffer: HtmlBuffer) {
        buffer.decreaseIndent()
        buffer.append("</div>")
    }
}
