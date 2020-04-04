package ca.ulaval.glo.model.report.file

enum class FileExtension(val values: List<String>) {
    JAVA(listOf("java"));

    fun matches(extension: String): Boolean {
        return values.contains(extension)
    }
}
