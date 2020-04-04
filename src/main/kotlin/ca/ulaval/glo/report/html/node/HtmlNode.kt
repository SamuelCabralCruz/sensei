package ca.ulaval.glo.report.html.node

import ca.ulaval.glo.report.html.HtmlBuffer

abstract class HtmlNode {
    private val children = mutableListOf<HtmlNode>()

    protected abstract fun openTag(buffer: HtmlBuffer)

    protected abstract fun closeTag(buffer: HtmlBuffer)

    fun addChild(node: HtmlNode): HtmlNode {
        children.add(node)
        return this
    }

    fun output(buffer: HtmlBuffer) {
        openAllTags(buffer)
        closeAllTags(buffer)
    }

    private fun openAllTags(buffer: HtmlBuffer) {
        openTag(buffer)
        children.forEach(fun(child) {
            child.openAllTags(buffer)
            child.closeAllTags(buffer)
        })
    }

    private fun closeAllTags(buffer: HtmlBuffer) {
        closeTag(buffer)
    }
}
