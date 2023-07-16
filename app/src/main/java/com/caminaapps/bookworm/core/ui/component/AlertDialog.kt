package com.caminaapps.bookworm.core.ui.component

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.caminaapps.bookworm.R

@Composable
fun ConfirmAlertDialog(
    onDismissRequest: () -> Unit,
    onConfirm: () -> Unit,
    titleText: String,
    messageText: String,
    confirmButtonText: String = stringResource(R.string.button_ok),
    cancelButtonText: String = stringResource(R.string.button_cancel),
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = { Text(titleText) },
        text = { Text(messageText) },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirm()
                    onDismissRequest()
                }
            ) {
                Text(confirmButtonText)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismissRequest) {
                Text(cancelButtonText)
            }
        }
    )
}
