package ca.ulaval.glo.model.report

import ca.ulaval.glo.model.Review
import ca.ulaval.glo.model.report.file.copyResourcesRecursively
import ca.ulaval.glo.model.report.file.createDirectories
import ca.ulaval.glo.model.report.file.deleteDirectory
import ca.ulaval.glo.model.report.file.replaceInFile
import ca.ulaval.glo.model.report.html.HtmlBuffer
import ca.ulaval.glo.model.report.html.node.*

class ReportGenerator {
    fun generate(review: Review, outputPath: String) {
        setUpOutputDirectory(outputPath)
        prepareAssets(outputPath)
        val htmlBuffer = HtmlBuffer()
        Root()
            .addChild(Headers(review.details.evaluationName!!))
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