package ca.ulaval.glo.report.html.node

import ca.ulaval.glo.report.html.HtmlBuffer

class Body : HtmlNode() {
    override fun openTag(buffer: HtmlBuffer) {
        buffer.append("<body>")
        buffer.increaseIndent()
        buffer.append("<div class='layout'>")
        buffer.increaseIndent()
    }

    override fun closeTag(buffer: HtmlBuffer) {
        buffer.decreaseIndent()
        buffer.append("</div>")
        buffer.decreaseIndent()
        buffer.append("</body>")
    }
}
