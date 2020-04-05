package ca.ulaval.glo.report.html.node

import ca.ulaval.glo.model.Review
import ca.ulaval.glo.model.ReviewComment
import ca.ulaval.glo.report.html.HtmlBuffer
import org.apache.commons.text.StringEscapeUtils

class SnippetsPanel(private val review: Review) : HtmlNode() {
    override fun openTag(buffer: HtmlBuffer) {
        buffer.append("<div class='snippets-panel'>")
        buffer.increaseIndent()
        appendSnippets(buffer)
    }

    private fun appendSnippets(buffer: HtmlBuffer) {
        buffer.append("<div class='snippets'>")
        buffer.increaseIndent()
        review.comments.forEach(fun(_, fileComments) {
            fileComments.sortBy { it.startingLine }
            fileComments.forEachIndexed(fun(index, fileComment) {
                appendSnippet(buffer, index, fileComment)
            })
        })
        buffer.decreaseIndent()
        buffer.append("</div>")
    }

    private fun appendSnippet(buffer: HtmlBuffer, index: Int, comment: ReviewComment) {
        buffer.append("<div key='${comment.hashCode()}-$index' class='snippet'>")
        buffer.increaseIndent()
        buffer.append("<pre ${getPreProps(comment)}><code class='language-${getBrush()}'>")
        buffer.increaseIndent()
        buffer.appendWithoutIndent(StringEscapeUtils.escapeHtml4(comment.codeSnippet))
        buffer.decreaseIndent()
        buffer.append("</code></pre>")
        buffer.decreaseIndent()
        buffer.append("</div>")
    }

    private fun getPreProps(comment: ReviewComment) =
        "class='line-numbers' ${getOtherAttributes(comment)}"

    private fun getOtherAttributes(comment: ReviewComment): String {
        val otherAttributes = mutableListOf<String>()
        otherAttributes.add("data-start='${comment.startingLine}'")
        otherAttributes.add("data-line='${comment.highlight.first}-${comment.highlight.last}'")
        otherAttributes.add("style='white-space:pre-wrap;'")
        return otherAttributes.joinToString(" ")
    }

    // TODO: determine dynamic brushes
    private fun getBrush() = "java"

    override fun closeTag(buffer: HtmlBuffer) {
        buffer.decreaseIndent()
        buffer.append("</div>")
    }
}