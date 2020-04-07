package ca.ulaval.glo.report

import ca.ulaval.glo.model.review.Review
import ca.ulaval.glo.report.file.copyResourcesRecursively
import ca.ulaval.glo.report.file.createDirectories
import ca.ulaval.glo.report.file.deleteDirectory
import ca.ulaval.glo.report.file.replaceInFile
import ca.ulaval.glo.report.html.HtmlBuffer
import ca.ulaval.glo.report.html.node.*

class ReportGenerator {
    fun generate(review: Review, outputPath: String) {
        setUpOutputDirectory(outputPath)
        prepareAssets(outputPath)
        val htmlBuffer = HtmlBuffer()
        Root()
            .addChild(Headers())
            .addChild(
                Body()
                    .addChild(CommentsPanel(review))
                    .addChild(SnippetsPanel(review))
            ).output(htmlBuffer)
        htmlBuffer.saveToFile("$outputPath/index.html")
    }

    private fun setUpOutputDirectory(path: String) {
        deleteDirectory(path)
        createDirectories(path)
    }

    private fun prepareAssets(path: String) {
        copyResourcesRecursively(
            this.javaClass.classLoader,
            "assets",
            path
        )
        replaceInFile("$path/assets/css/fonts.css", "<projectBasePath>", path)
    }
}
