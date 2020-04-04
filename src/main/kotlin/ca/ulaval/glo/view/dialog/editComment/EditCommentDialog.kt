package ca.ulaval.glo.view.dialog.editComment

import ca.ulaval.glo.model.CommentTag
import ca.ulaval.glo.model.ReviewComment
import ca.ulaval.glo.model.ReviewCommentDetails
import ca.ulaval.glo.model.getAllPresetReviewCommentDetails
import ca.ulaval.glo.view.dialog.Label
import ca.ulaval.glo.view.dialog.Panel
import ca.ulaval.glo.view.dialog.SimpleKeyListener
import com.intellij.openapi.ui.ComboBox
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.ui.components.JBCheckBox
import com.intellij.ui.components.JBScrollPane
import com.intellij.ui.components.JBTextArea
import java.awt.Dimension
import java.awt.event.ActionListener
import java.awt.event.ItemListener
import javax.swing.JComponent
import javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
import javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED

class EditCommentDialog() : DialogWrapper(true) {
    // TODO: find a way to set focus on description field on open
    // TODO: add shortcuts for checkboxes + on key press focus text area
    // TODO: make dropdown searchable
    private var selectedPreset: ReviewCommentDetails? = null
    private val presets = ComboBox(getAllPresetReviewCommentDetails())
    private val descriptionField = JBTextArea()
    private var tagFields = mutableMapOf<CommentTag, JBCheckBox>()

    constructor(reviewComment: ReviewComment) : this() {
        fillFormWithReviewCommentDetails(reviewComment.details)
    }

    init {
        init()
        title = "Edit Comment"
    }

    override fun createCenterPanel(): JComponent? {
        val panel = Panel(700, 400, 5)
        val gb = panel.gridBag
        panel.add(Label("Label"), gb.next())
        panel.add(createPresetSelector(), gb.nextLine().coverLine())
        panel.add(Label("Description"), gb.nextLine().coverLine())
        panel.add(createDescriptionField(), gb.nextLine().coverLine())
        panel.add(Label("Tags"), gb.nextLine().next())
        CommentTag.values().forEach(fun(tag) {
            val checkbox = JBCheckBox(tag.value)
            checkbox.addActionListener(ActionListener(fun(event) {
                selectedPreset = null
            }))
            tagFields[tag] = checkbox
            panel.add(checkbox, gb.next())
        })
        return panel
    }

    private fun createPresetSelector(): ComboBox<ReviewCommentDetails> {
        presets.selectedItem = null
        presets.isEditable = true
        presets.addItemListener(ItemListener(fun(event) {
            if (presets.selectedItem == event.item) {
                if (presets.selectedItem !is ReviewCommentDetails) {
                    if (selectedPreset != null) {
                        clearDescription()
                        clearTags()
                        selectedPreset = null
                    }
                    return
                } else {
                    val selectedItem = presets.selectedItem as ReviewCommentDetails
                    selectedPreset = selectedItem
                    fillFormWithReviewCommentDetails(selectedItem)
                }
            }
        }))
        return presets
    }

    private fun createDescriptionField(): JBScrollPane {
        descriptionField.preferredSize = Dimension(550, 300)
        descriptionField.lineWrap = true
        descriptionField.addKeyListener(SimpleKeyListener(fun(_) {
            selectedPreset = null
        }))
        return JBScrollPane(descriptionField, VERTICAL_SCROLLBAR_AS_NEEDED, HORIZONTAL_SCROLLBAR_NEVER)
    }

    private fun fillFormWithReviewCommentDetails(reviewCommentDetails: ReviewCommentDetails) {
        presets.editor.item = reviewCommentDetails.label
        descriptionField.text = reviewCommentDetails.description
        clearTags()
        reviewCommentDetails.tags.forEach(fun(tag) {
            val checkbox = tagFields[tag]
            if (checkbox != null) checkbox.isSelected = true
        })
    }

    private fun clearDescription() {
        descriptionField.text = ""
    }

    private fun clearTags() {
        tagFields.values.forEach(fun(checkbox) {
            checkbox.isSelected = false
        })
    }

    fun getDetails(): ReviewCommentDetails {
        return ReviewCommentDetails(getLabel(), getDescription(), getTags())
    }

    private fun getLabel() = presets.editor.item.toString()

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
