package ca.ulaval.glo.report.file

import ca.ulaval.glo.report.file.comparator.PathStructure
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

fun createDirectories(_path: String) {
    val path = Paths.get(_path)
    if (!Files.exists(path)) {
        Files.createDirectories(path)
    }
}

fun deleteDirectory(_path: String) {
    val directoryToBeDeleted = File(_path)
    val allContents: Array<File>? = directoryToBeDeleted.listFiles()
    if (allContents != null) {
        for (file in allContents) {
            deleteDirectory(file.path)
        }
    }
    directoryToBeDeleted.delete()
}

fun replaceInFile(_path: String, oldText: String, newText: String) {
    val path = Paths.get(_path)
    if (!Files.exists(path)) return
    val content = String(Files.readAllBytes(path))
    val editedContent = content.replace(oldText, newText)
    Files.write(path, editedContent.toByteArray())
}

fun extractPathStructure(filePath: String): PathStructure {
    val partitions = filePath.split("/").filter(fun(partition) = partition != "")
    val containingFolder = partitions.subList(0, partitions.lastIndex).joinToString("/")
    val fileName = partitions.last()
    val fileNamePartitions = fileName.split(".")
    val extension = fileNamePartitions.slice(IntRange(1, fileNamePartitions.lastIndex)).joinToString(".")
    return PathStructure(filePath, partitions, containingFolder, fileName, extension)
}
