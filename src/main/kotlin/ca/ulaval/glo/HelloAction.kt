package ca.ulaval.glo

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.LangDataKeys
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.util.TextRange
import java.lang.Integer.max
import java.lang.Integer.min

class HelloAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val caret = e.getData(LangDataKeys.CARET)
        val editor = e.getData(LangDataKeys.EDITOR)
        if (editor != null && caret != null) {
            val chunkStartLine = caret.selectionStartPosition.line
            val chunkEndLine = caret.selectionEndPosition.line
            displayText(editor, chunkStartLine, chunkEndLine, e)
        }
    }

    private fun displayText(
        editor: Editor,
        startLine: Int,
        endLine: Int,
        e: AnActionEvent
    ) {
        val selectionStartLine = max(startLine - 10, 0)
        val selectionEndLine = min(endLine + 10, editor.document.lineCount)
        val startOffset = editor.document.getLineStartOffset(selectionStartLine)
        val endOffset = editor.document.getLineEndOffset(selectionEndLine)
        val textRange = TextRange.from(startOffset, endOffset - startOffset)
        val text = editor.document.getText(textRange)
        Messages.showMessageDialog(e.project, text, "sensei", Messages.getInformationIcon())
    }
}
