package ca.ulaval.glo.model.report.html.node

import ca.ulaval.glo.model.report.file.filter.CssFileFilter
import ca.ulaval.glo.model.report.file.getAllResourcesPathRecursively
import ca.ulaval.glo.model.report.html.HtmlBuffer

class Headers(private val title: String) : HtmlNode() {
    override fun openTag(buffer: HtmlBuffer) {
        buffer.append("<head>")
        buffer.increaseIndent()
        appendTitle(buffer)
        appendCssFiles(buffer);
        // TODO copyJsFiles();
        appendMeta(buffer)
    }

    private fun appendTitle(buffer: HtmlBuffer) {
        buffer.append("<title>")
        buffer.increaseIndent()
        buffer.append(title)
        buffer.decreaseIndent()
        buffer.append("</title>")
    }

    private fun appendCssFiles(buffer: HtmlBuffer) {
        val cssResources =
            getAllResourcesPathRecursively(
                this.javaClass.classLoader, "assets",
                CssFileFilter()
            )
        cssResources.forEach(fun(cssResource) {
            buffer.append("<link rel='stylesheet' href='$cssResource'>")
        })
    }

    private fun appendMeta(buffer: HtmlBuffer) {
        buffer.append("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>")
    }

    override fun closeTag(buffer: HtmlBuffer) {
        buffer.decreaseIndent()
        buffer.append("</head>")
    }
}
