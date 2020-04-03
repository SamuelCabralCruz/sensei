package ca.ulaval.glo.model.report

import java.io.BufferedReader
import java.io.File
import java.io.FileOutputStream
import java.io.InputStreamReader
import java.util.jar.JarFile

fun copyResourcesRecursively(classLoader: ClassLoader, resourcePath: String, outputPath: String) {
    val assetsPath = classLoader.getResources(resourcePath).nextElement().path
    val jarPath = assetsPath.replace("file:", "").replace("!/$resourcePath/", "")
    val jarFile = JarFile(jarPath)
    val entries = jarFile.entries()
    while (entries.hasMoreElements()) {
        val entry = entries.nextElement()
        val entryPath = entry.name
        if (entryPath.startsWith(resourcePath) && !entry.isDirectory) {
            copyResourceFile(classLoader, entryPath, outputPath)
        }
    }
}

fun copyResourceFile(classLoader: ClassLoader, resourcePath: String, outputPath: String) {
    val pathPartition = resourcePath.split("/")
    val parentDirectory = pathPartition.subList(0, pathPartition.lastIndex).joinToString("/")
    createDirectoriesIfNeeded("$outputPath/$parentDirectory")
    val inputStream = classLoader.getResourceAsStream(resourcePath) ?: return
    val inputStreamReader = BufferedReader(InputStreamReader(inputStream)).read()
    createDirectoriesIfNeeded("$outputPath/$parentDirectory")
    val outputStream = FileOutputStream(File("$outputPath/$resourcePath"))
    outputStream.write(inputStreamReader)
}
