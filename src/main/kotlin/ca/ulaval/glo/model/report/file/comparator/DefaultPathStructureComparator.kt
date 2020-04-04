package ca.ulaval.glo.model.report.file.comparator

class DefaultPathStructureComparator : PathStructureComparator {
    override fun isApplicable(p0: PathStructure, p1: PathStructure): Boolean {
        return true
    }

    override fun compare(p0: PathStructure, p1: PathStructure): Int {
        return p0.fullPath.compareTo(p1.fullPath)
    }
}
