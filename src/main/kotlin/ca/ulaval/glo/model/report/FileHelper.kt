package ca.ulaval.glo.model.report

import java.nio.file.Files
import java.nio.file.Paths

fun createDirectoriesIfNeeded(_path: String) {
    val path = Paths.get(_path)
    if (!Files.exists(path)) {
        Files.createDirectories(path)
    }
}

fun deleteDirectoryIfExist(_path: String) {
    val path = Paths.get(_path)
    Files.deleteIfExists(path)
}
