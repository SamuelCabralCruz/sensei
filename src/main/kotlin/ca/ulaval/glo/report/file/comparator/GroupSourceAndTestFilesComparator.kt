package ca.ulaval.glo.report.file.comparator

import ca.ulaval.glo.report.file.extractPathStructure

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
}
