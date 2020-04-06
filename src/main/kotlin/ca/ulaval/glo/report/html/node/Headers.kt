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
        appendFaviconImages(buffer)
        appendMeta(buffer)
    }

    private fun appendTitle(buffer: HtmlBuffer) {
        buffer.append("<title>")
        buffer.increaseIndent()
        buffer.append("Sensei - Code Reviews")
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

    private fun appendFaviconImages(buffer: HtmlBuffer) {
        buffer.append("<link rel='apple-touch-icon' sizes='180x180' href='assets/img/favicon/apple-touch-icon.png'>")
        buffer.append("<link rel='icon' type='image/png' sizes='32x32' href='assets/img/favicon/favicon-32x32.png'>")
        buffer.append("<link rel='icon' type='image/png' sizes='16x16' href='assets/img/favicon/favicon-16x16.png'>")
        buffer.append("<link rel='manifest' href='assets/img/favicon/site.webmanifest'>")
        buffer.append("<link rel='mask-icon' href='assets/img/favicon/safari-pinned-tab.svg' color='#65828a'>")
        buffer.append("<link rel='shortcut icon' href='assets/img/favicon/favicon.ico'>")
        buffer.append("<meta name='apple-mobile-web-app-title' content='Sensei'>")
        buffer.append("<meta name='application-name' content='Sensei'>")
        buffer.append("<meta name='msapplication-TileColor' content='#da532c'>")
        buffer.append("<meta name='msapplication-config' content='assets/img/favicon/browserconfig.xml'>")
        buffer.append("<meta name='theme-color' content='#ffffff'>")
    }

    private fun appendMeta(buffer: HtmlBuffer) {
        buffer.append("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>")
    }

    override fun closeTag(buffer: HtmlBuffer) {
        buffer.decreaseIndent()
        buffer.append("</head>")
    }
}
