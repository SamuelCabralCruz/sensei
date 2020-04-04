package ca.ulaval.glo.report.html.node

import ca.ulaval.glo.report.html.HtmlBuffer

class Root : HtmlNode() {
    override fun openTag(buffer: HtmlBuffer) {
        buffer.append("<html>")
        buffer.increaseIndent()
    }

    override fun closeTag(buffer: HtmlBuffer) {
        buffer.decreaseIndent()
        buffer.append("</html>")
    }
}
