package ca.ulaval.glo.report.file

import ca.ulaval.glo.report.file.filter.AcceptAllFileFilter
import java.io.File
import java.io.FileFilter
import java.io.FileOutputStream
import java.util.*
import java.util.jar.JarEntry
import java.util.jar.JarFile

fun getAllResourcesPathRecursively(
    classLoader: ClassLoader,
    resourcePath: String,
    filter: FileFilter = AcceptAllFileFilter()
): List<String> {
    val entries = getResourcesEntries(classLoader, resourcePath)
    val resourcesPath = mutableListOf<String>()
    while (entries.hasMoreElements()) {
        val entry = entries.nextElement()
        val entryPath = entry.name
        if (filter.accept(File(entryPath))) resourcesPath.add(entryPath)
    }
    return resourcesPath.toList()
}

fun copyResourcesRecursively(classLoader: ClassLoader, resourcePath: String, outputPath: String) {
    val entries = getResourcesEntries(classLoader, resourcePath)
    while (entries.hasMoreElements()) {
        val entry = entries.nextElement()
        val entryPath = entry.name
        if (entryPath.startsWith(resourcePath) && !entry.isDirectory) {
            copyResourceFile(classLoader, entryPath, outputPath)
        }
    }
}

private fun getResourcesEntries(classLoader: ClassLoader, resourcePath: String): Enumeration<JarEntry> {
    val assetsPath = classLoader.getResources(resourcePath).nextElement().path
    val jarPath = assetsPath.replace("file:", "").replace("!/$resourcePath/", "")
    val jarFile = JarFile(jarPath)
    return jarFile.entries()
}

fun copyResourceFile(classLoader: ClassLoader, resourcePath: String, outputPath: String) {
    val inputStream = classLoader.getResourceAsStream(resourcePath) ?: return
    val inputStreamReader = inputStream.readBytes()
    val pathPartition = resourcePath.split("/")
    val parentDirectory = pathPartition.subList(0, pathPartition.lastIndex).joinToString("/")
    createDirectories("$outputPath/$parentDirectory")
    val outputStream = FileOutputStream(File("$outputPath/$resourcePath"))
    outputStream.write(inputStreamReader)
    outputStream.flush()
    outputStream.close()
}
