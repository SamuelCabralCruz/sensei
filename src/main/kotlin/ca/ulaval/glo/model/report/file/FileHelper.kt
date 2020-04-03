package ca.ulaval.glo.model.report.file

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
