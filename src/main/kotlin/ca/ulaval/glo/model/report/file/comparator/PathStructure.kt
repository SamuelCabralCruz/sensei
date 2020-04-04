package ca.ulaval.glo.model.report.file.comparator

class PathStructure(
    val fullPath: String,
    val partitions: List<String>,
    val containingFolder: String,
    val fileName: String,
    val extension: String
)
