package ca.ulaval.glo

import ca.ulaval.glo.persistence.ReviewPersistence
import ca.ulaval.glo.state.ReviewComment
import com.intellij.codeInsight.daemon.DaemonCodeAnalyzer
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.LangDataKeys
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.util.TextRange
import java.lang.Integer.max
import java.lang.Integer.min


class AddCommentAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val caret = e.getData(LangDataKeys.CARET)
        val editor = e.getData(LangDataKeys.EDITOR)
        if (editor != null && caret != null) {
            val chunkStartLine = caret.selectionStartPosition.line
            val chunkEndLine = caret.selectionEndPosition.line
            val codeSnippet = getCodeSnippet(editor, chunkStartLine, chunkEndLine)
            Messages.showMessageDialog(codeSnippet, "sensei", Messages.getInformationIcon())
            val reviewComment = ReviewComment()
            val filePath = e.getData(LangDataKeys.VIRTUAL_FILE)!!.path
            val projectBasePath = e.project?.basePath
            reviewComment.filePath = filePath.replace(projectBasePath!!, "")
            reviewComment.startOffset = editor.document.getLineEndOffset(chunkStartLine)
            reviewComment.codeSnippet = codeSnippet
            val review = ReviewPersistence.getInstance().state
            if (review != null) {
                val reviewComments = review.comments.getOrDefault(reviewComment.filePath, mutableListOf())
                reviewComments.add(reviewComment)
                review.comments[reviewComment.filePath] = reviewComments
                val containingFile = e.getData(LangDataKeys.PSI_FILE)?.originalFile!!
                DaemonCodeAnalyzer.getInstance(e.project).restart(containingFile)
            }
        }
    }

    private fun getCodeSnippet(
        editor: Editor,
        startLine: Int,
        endLine: Int
    ): String {
        val selectionStartLine = max(startLine - 10, 0)
        val selectionEndLine = min(endLine + 10, editor.document.lineCount - 1)
        val startOffset = editor.document.getLineStartOffset(selectionStartLine)
        val endOffset = editor.document.getLineEndOffset(selectionEndLine)
        val textRange = TextRange.from(startOffset, endOffset - startOffset)
        return editor.document.getText(textRange)
    }
}
