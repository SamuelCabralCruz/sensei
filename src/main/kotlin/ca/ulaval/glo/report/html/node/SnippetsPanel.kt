package ca.ulaval.glo.report.html.node

import ca.ulaval.glo.model.Review
import ca.ulaval.glo.report.html.HtmlBuffer
import org.apache.commons.text.StringEscapeUtils

class SnippetsPanel(private val review: Review) : HtmlNode() {
    override fun openTag(buffer: HtmlBuffer) {
        buffer.append("<div class='snippets-panel'>")
        buffer.increaseIndent()
        appendSnippets(buffer)
    }

    private fun appendSnippets(buffer: HtmlBuffer) {
        val comment = review.comments.values.first().first()
        // TODO: determine dynamic brushes
        buffer.append("<pre class='line-numbers' data-start='${comment.startingLine}' data-line='${comment.highlight.first}-${comment.highlight.last}' style='white-space:pre-wrap;'><code class='language-java'>")
        buffer.increaseIndent()
        buffer.appendWithoutIndent(StringEscapeUtils.escapeHtml4(comment.codeSnippet))
        buffer.decreaseIndent()
        buffer.append("</code></pre>")
    }

    override fun closeTag(buffer: HtmlBuffer) {
        buffer.decreaseIndent()
        buffer.append("</div>")
    }
}
