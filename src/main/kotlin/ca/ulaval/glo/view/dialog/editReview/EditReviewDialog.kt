package ca.ulaval.glo.view.dialog.editReview

import ca.ulaval.glo.model.ReviewDetails
import ca.ulaval.glo.view.dialog.Label
import ca.ulaval.glo.view.dialog.Panel
import ca.ulaval.glo.view.dialog.validation.ValidationError
import ca.ulaval.glo.view.dialog.validation.invalidField
import ca.ulaval.glo.view.dialog.validation.missingField
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.ui.components.JBTextField
import java.lang.Integer.parseInt
import javax.swing.JComponent

class EditReviewDialog() : DialogWrapper(true) {
    private val reviewerField = JBTextField()
    private val teamNumberField = JBTextField()
    private val evaluationNameField = JBTextField()

    init {
        init()
        title = "Create Review"
    }

    override fun createCenterPanel(): JComponent? {
        val panel = Panel(300, 200, 4)
        val gb = panel.gridBag
        panel.add(Label("Reviewer"), gb.nextLine().next())
        panel.add(reviewerField, gb.nextLine().coverLine())
        panel.add(Label("Team Number"), gb.nextLine().next())
        panel.add(teamNumberField, gb.nextLine().coverLine())
        panel.add(Label("Evaluation Name"), gb.nextLine().next())
        panel.add(evaluationNameField, gb.nextLine().coverLine())
        return panel
    }

    override fun doOKAction() {
        try {
            validateInputs()
        } catch (e: ValidationError) {
            return
        }
        close(0)
    }

    private fun validateInputs() {
        checkMissingReviewer()
        checkMissingTeamNumber()
        checkMissingEvaluationName()
        checkValidTeamNumber()
    }

    private fun checkMissingReviewer() {
        if (reviewerField.text.isBlank()) {
            missingField("Reviewer")
            throw ValidationError()
        }
    }

    private fun checkMissingTeamNumber() {
        if (teamNumberField.text.isBlank()) {
            missingField("Team Number")
            throw ValidationError()
        }
    }

    private fun checkMissingEvaluationName() {
        if (evaluationNameField.text.isBlank()) {
            missingField("Evaluation Name")
            throw ValidationError()
        }
    }

    private fun checkValidTeamNumber() {
        try {
            parseInt(teamNumberField.text)
        } catch (e: NumberFormatException) {
            invalidField("Team Number", "Team Number must be an integer")
            throw ValidationError()
        }
    }

    fun getDetails(): ReviewDetails {
        return ReviewDetails(getReviewer(), getTeamNumber(), getEvaluationName())
    }

    private fun getReviewer(): String? {
        return reviewerField.text
    }

    private fun getTeamNumber(): Int? {
        return teamNumberField.text.toInt()
    }

    private fun getEvaluationName(): String? {
        return evaluationNameField.text
    }
}
