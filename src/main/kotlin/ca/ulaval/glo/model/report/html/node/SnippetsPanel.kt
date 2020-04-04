package ca.ulaval.glo.model.report.html.node

import ca.ulaval.glo.model.Review
import ca.ulaval.glo.model.report.html.HtmlBuffer
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
        // TODO: introduce highlight and change starting line to be the real starting line number
        buffer.append("<pre class='line-numbers' data-start='${comment.startingLine - 10}' data-line='${comment.startingLine}-${comment.startingLine + 5}' style='white-space:pre-wrap;'><code class='language-java'>")
        buffer.increaseIndent()
        buffer.append(StringEscapeUtils.escapeHtml4(comment.codeSnippet))
        buffer.decreaseIndent()
        buffer.append("</code></pre>")
    }

    override fun closeTag(buffer: HtmlBuffer) {
        buffer.decreaseIndent()
        buffer.append("</div>")
    }
}
