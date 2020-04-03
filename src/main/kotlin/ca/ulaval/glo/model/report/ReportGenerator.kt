package ca.ulaval.glo.model.report


class ReportGenerator {
    fun generate(outputPath: String) {
        setUpOutputDirectory(outputPath)
        copyAssets(outputPath)
        // todo: create basic html file
    }

    private fun setUpOutputDirectory(path: String) {
        deleteDirectoryIfExist(path)
        createDirectoriesIfNeeded(path)
    }

    private fun copyAssets(path: String) {
        copyResourcesRecursively(this.javaClass.classLoader, "assets", path)
    }
}
