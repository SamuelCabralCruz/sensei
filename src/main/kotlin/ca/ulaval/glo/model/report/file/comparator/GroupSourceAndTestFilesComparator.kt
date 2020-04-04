package ca.ulaval.glo.model.report.file.comparator

interface PathStructureComparator : Comparator<PathStructure> {
    fun isApplicable(p0: PathStructure, p1: PathStructure): Boolean
}

private val pathStructureComparators = listOf(JavaPackagePathStructureComparator(), DefaultPathStructureComparator())

class GroupSourceAndTestFilesPathComparator : Comparator<String> {
    override fun compare(filePath0: String, filePath1: String): Int {
        val pathStructure0 = extractPathStructure(filePath0)
        val pathStructure1 = extractPathStructure(filePath1)
        return pathStructureComparators.find(fun(comparator): Boolean {
            return comparator.isApplicable(pathStructure0, pathStructure1)
        })!!.compare(pathStructure0, pathStructure1)
    }

    private fun extractPathStructure(filePath: String): PathStructure {
        val partitions = filePath.split("/").filter(fun(partition) = partition != "")
        val containingFolder = partitions.subList(0, partitions.lastIndex).joinToString("/")
        val fileName = partitions.last()
        val fileNamePartitions = fileName.split(".")
        val extension = fileNamePartitions.slice(IntRange(1, fileNamePartitions.lastIndex)).joinToString(".")
        return PathStructure(filePath, partitions, containingFolder, fileName, extension)
    }
}
