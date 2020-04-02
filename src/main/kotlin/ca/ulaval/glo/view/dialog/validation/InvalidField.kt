package ca.ulaval.glo.view.dialog.validation

import com.intellij.openapi.ui.Messages

fun invalidField(fieldName: String, reason: String) {
    Messages.showErrorDialog("$fieldName is invalid.\nReason: $reason", "Invalid Field")
}
