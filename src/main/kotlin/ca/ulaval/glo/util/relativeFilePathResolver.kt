package ca.ulaval.glo.util

import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile

fun getRelativeFilePath(project: Project, virtualFile: VirtualFile): String {
    val projectBasePath = project.basePath ?: return ""
    val virtualFilePath = virtualFile.path
    return virtualFilePath.replace(projectBasePath, "")
}
