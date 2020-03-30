package ca.ulaval.glo.view.comment

import ca.ulaval.glo.persistence.review.state.ReviewComment
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBScrollPane
import com.intellij.ui.components.JBTextArea
import com.intellij.uiDesigner.core.AbstractLayout
import com.intellij.util.ui.GridBag
import com.intellij.util.ui.JBUI
import com.intellij.util.ui.UIUtil
import java.awt.Dimension
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import javax.swing.JComponent
import javax.swing.JPanel
import javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
import javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED

class EditCommentDialog() : DialogWrapper(true) {
    private val panel = JPanel(GridBagLayout())
    private val descriptionField = JBTextArea()
    private val descriptionScrollPane =
        JBScrollPane(descriptionField, VERTICAL_SCROLLBAR_AS_NEEDED, HORIZONTAL_SCROLLBAR_NEVER)

    constructor(comment: ReviewComment) : this() {
        descriptionField.text = comment.details.description
    }

    init {
        init()
        title = "Edit Comment"
    }

    override fun createCenterPanel(): JComponent? {
        descriptionField.preferredSize = Dimension(550, 300)
        descriptionField.lineWrap = true

        val gb = GridBag()
            .setDefaultInsets(0, 0, AbstractLayout.DEFAULT_VGAP, AbstractLayout.DEFAULT_HGAP)
            .setDefaultWeightX(1.0)
            .setDefaultFill(GridBagConstraints.HORIZONTAL)

        panel.add(label("Description"), gb.nextLine().next().weightx(0.2))
        panel.add(descriptionScrollPane, gb.nextLine().next().weightx(0.8))

        return panel
    }

    private fun label(text: String): JComponent {
        val label = JBLabel(text)
        label.componentStyle = UIUtil.ComponentStyle.SMALL
        label.fontColor = UIUtil.FontColor.BRIGHTER
        label.border = JBUI.Borders.empty(0, 5, 2, 0)
        return label
    }

    fun getDescription(): String {
        return descriptionField.text
    }
}
