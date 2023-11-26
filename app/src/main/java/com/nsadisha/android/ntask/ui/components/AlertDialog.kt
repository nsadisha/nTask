package com.nsadisha.android.ntask.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign

@Composable
fun AlertDialog(
    show: Boolean,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    confirmBtnText: String = "Confirm",
    dismissBtnText: String = "Dismiss"
) {
    if (show) {
        AlertDialog(
            title = {
                Text(text = dialogTitle, color = MaterialTheme.colorScheme.onSurface)
            },
            text = {
                Text(
                    text = dialogText,
                    color = MaterialTheme.colorScheme.secondary,
                    textAlign = TextAlign.Start
                )
            },
            onDismissRequest = {
                onDismissRequest()
            },
            confirmButton = {
                TextButton(
                    onClick = { onConfirmation() }
                ) {
                    Text(confirmBtnText, color = MaterialTheme.colorScheme.primary)
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { onDismissRequest() }
                ) {
                    Text(dismissBtnText, color = MaterialTheme.colorScheme.primary)
                }
            }
        )
    }
}