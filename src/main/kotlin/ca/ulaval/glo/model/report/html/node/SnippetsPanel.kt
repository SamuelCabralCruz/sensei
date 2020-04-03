package ca.ulaval.glo.model.report.html.node

import ca.ulaval.glo.model.report.html.HtmlBuffer

class SnippetsPanel : HtmlNode() {
    override fun openTag(buffer: HtmlBuffer) {
        buffer.append("<div class='snippets-panel'>")
        buffer.increaseIndent()
    }

    override fun closeTag(buffer: HtmlBuffer) {
        buffer.decreaseIndent()
        buffer.append("</div>")
    }
}
