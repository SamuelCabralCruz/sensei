package ca.ulaval.glo.model.report.file.filter

import java.io.File
import java.io.FileFilter

class CssFileFilter : FileFilter {
    override fun accept(file: File?): Boolean {
        file ?: return false
        return file.path.endsWith(".css")
    }
}
