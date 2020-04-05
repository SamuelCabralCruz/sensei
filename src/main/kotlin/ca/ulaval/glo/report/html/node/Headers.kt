package ca.ulaval.glo.report.html.node

import ca.ulaval.glo.report.file.filter.CssFileFilter
import ca.ulaval.glo.report.file.filter.JsFileFilter
import ca.ulaval.glo.report.file.getAllResourcesPathRecursively
import ca.ulaval.glo.report.html.HtmlBuffer

class Headers() : HtmlNode() {
    override fun openTag(buffer: HtmlBuffer) {
        buffer.append("<head>")
        buffer.increaseIndent()
        appendTitle(buffer)
        appendCssFiles(buffer)
        appendJsFiles(buffer)
        appendMeta(buffer)
    }

    private fun appendTitle(buffer: HtmlBuffer) {
        buffer.append("<title>")
        buffer.increaseIndent()
        buffer.append("Sensei - Code Review")
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
            buffer.append("<link rel='stylesheet' href='$cssResource'/>")
        })
    }

    private fun appendJsFiles(buffer: HtmlBuffer) {
        val jsResources =
            getAllResourcesPathRecursively(this.javaClass.classLoader, "assets", JsFileFilter())
        jsResources.forEach(fun(jsResource) {
            buffer.append("<script src='$jsResource'></script>")
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
