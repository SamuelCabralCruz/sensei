package ca.ulaval.glo.view.comment

import ca.ulaval.glo.persistence.review.state.CommentTag
import ca.ulaval.glo.persistence.review.state.ReviewComment
import ca.ulaval.glo.persistence.review.state.ReviewCommentDetails
import com.intellij.openapi.ui.ComboBox
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.openapi.ui.Messages
import com.intellij.ui.components.JBCheckBox
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
import java.awt.event.ItemListener
import javax.swing.JComponent
import javax.swing.JPanel
import javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
import javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED

class EditCommentDialog() : DialogWrapper(true) {
    // TODO: find a way to set focus on description field on open
    // TODO: add shortcuts for checkboxes + on key press focus text area
    private val descriptionField = JBTextArea()
    private var tagFields = mutableMapOf<CommentTag, JBCheckBox>()

    constructor(comment: ReviewComment) : this() {
        descriptionField.text = comment.details.description
        comment.details.tags.forEach(fun(tag) {
            val checkbox = tagFields[tag]
            if (checkbox != null) checkbox.isSelected = true
        })
    }

    init {
        init()
        title = "Edit Comment"
    }

    override fun createCenterPanel(): JComponent? {
        // TODO: fill combo box with preset values
        val presets = ComboBox(arrayOf("blank", "apple", "tomato"))

        descriptionField.preferredSize = Dimension(550, 300)
        descriptionField.lineWrap = true
        val descriptionScrollPane =
            JBScrollPane(descriptionField, VERTICAL_SCROLLBAR_AS_NEEDED, HORIZONTAL_SCROLLBAR_NEVER)

        val panel = JPanel(GridBagLayout())
        val gb = GridBag()
            .setDefaultInsets(0, 5, AbstractLayout.DEFAULT_VGAP, AbstractLayout.DEFAULT_HGAP)
            .setDefaultWeightX(1.0)
            .setDefaultFill(GridBagConstraints.HORIZONTAL)
        panel.minimumSize = Dimension(700, 400)

        panel.add(label("Select template"), gb.next())
        panel.add(presets, gb.nextLine().coverLine())
        panel.add(label("Description"), gb.nextLine().coverLine())
        panel.add(descriptionScrollPane, gb.nextLine().coverLine())
        panel.add(label("Tags"), gb.nextLine().next())
        CommentTag.values().forEach(fun(tag) {
            val checkbox = JBCheckBox(tag.getKey())
            tagFields[tag] = checkbox
            panel.add(checkbox, gb.next())
        })

        presets.addItemListener(ItemListener(fun(event) {
            if (presets.selectedItem == event.item) {
                // TODO: change description and tags
                Messages.showInfoMessage(event.item.toString(), "item changed")
            }
        }))

        return panel
    }

    private fun label(text: String): JComponent {
        val label = JBLabel(text)
        label.componentStyle = UIUtil.ComponentStyle.LARGE
        label.fontColor = UIUtil.FontColor.BRIGHTER
        label.border = JBUI.Borders.empty(0, 5, 2, 0)
        return label
    }

    fun getDetails(): ReviewCommentDetails {
        val details = ReviewCommentDetails()
        details.description = getDescription()
        details.tags = getTags()
        return details
    }

    private fun getDescription(): String {
        return descriptionField.text.trim().replace(Regex("[^\\S ]{2,}"), "\n")
    }

    private fun getTags(): MutableList<CommentTag> {
        val selectedTags = mutableListOf<CommentTag>()
        tagFields.entries.forEach(fun(entry) {
            if (entry.value.isSelected) selectedTags.add(entry.key)
        })
        return selectedTags
    }
}
