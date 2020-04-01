package ca.ulaval.glo.view.dialog

import com.intellij.ui.components.JBLabel
import com.intellij.util.ui.JBUI
import com.intellij.util.ui.UIUtil

class Label(text: String) : JBLabel(text) {
    init {
        componentStyle = UIUtil.ComponentStyle.LARGE
        fontColor = UIUtil.FontColor.BRIGHTER
        border = JBUI.Borders.empty(0, 5, 2, 0)
    }
}
