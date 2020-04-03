package ca.ulaval.glo.model.report.html

import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.lang.Integer.max

class HtmlBuffer {
    private var incrementationLevel = 0
    private val buffer = StringBuffer()

    fun append(content: String) {
        buffer.append("${getIndentation()}$content\n")
    }

    private fun getIndentation(): String {
        return "\t".repeat(incrementationLevel)
    }

    fun increaseIndent() {
        incrementationLevel++
    }

    fun decreaseIndent() {
        incrementationLevel = max(0, incrementationLevel - 1)
    }

    fun saveToFile(path: String) {
        val bufferedWriter = BufferedWriter(FileWriter(File(path)))
        bufferedWriter.write(buffer.toString())
        bufferedWriter.flush()
        bufferedWriter.close()
    }
}
