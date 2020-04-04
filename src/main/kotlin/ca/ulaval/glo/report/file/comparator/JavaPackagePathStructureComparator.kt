package ca.ulaval.glo.report.file.comparator

import ca.ulaval.glo.report.file.FileExtension.JAVA

class JavaPackagePathStructureComparator : PathStructureComparator {
    override fun isApplicable(p0: PathStructure, p1: PathStructure): Boolean {
        return JAVA.matches(p0.extension) && JAVA.matches(p1.extension) && isPackagedFile(p0) && isPackagedFile(p1)
    }

    private fun isPackagedFile(pathStructure: PathStructure): Boolean {
        return pathStructure.containingFolder.contains(Regex("src/main|src/test|src/it"))
    }

    override fun compare(p0: PathStructure, p1: PathStructure): Int {
        return extractPackageName(p0).compareTo(extractPackageName(p1))
    }

    private fun extractPackageName(pathStructure: PathStructure): String {
        val subPartitions = pathStructure.partitions.toMutableList()
        val startingIndex = subPartitions.indexOf("src") + 2
        return subPartitions.slice(IntRange(startingIndex, subPartitions.lastIndex)).joinToString("/")
    }
}
