package ca.ulaval.glo.model.report.html.node

import ca.ulaval.glo.model.report.html.HtmlBuffer

class CommentsPanel(private val title: String) : HtmlNode() {
    override fun openTag(buffer: HtmlBuffer) {
        buffer.append("<div class='comments-panel'>")
        buffer.increaseIndent()
        appendTitle(buffer)
    }

    private fun appendTitle(buffer: HtmlBuffer) {
        buffer.append("<h1>")
        buffer.increaseIndent()
        buffer.append(title)
        buffer.decreaseIndent()
        buffer.append("</h1>")
    }

    override fun closeTag(buffer: HtmlBuffer) {
        buffer.decreaseIndent()
        buffer.append("</div>")
    }
}
