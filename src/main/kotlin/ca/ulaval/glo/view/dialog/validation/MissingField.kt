package ca.ulaval.glo.view.dialog.validation

import com.intellij.openapi.ui.Messages

fun missingField(fieldName: String) {
    Messages.showErrorDialog("$fieldName is missing.", "Missing Field")
}
