package com.caminaapps.bookworm.core.presentation.components

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.caminaapps.bookworm.R
import com.caminaapps.bookworm.util.hasPermanentlyDenied
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState

@ExperimentalPermissionsApi
@Composable
fun CameraPermissionAlertDialog(
    onDismissRequest: () -> Unit,
    onConfirm: () -> Unit,
    cameraPermissionState: PermissionState,
) {
    val context = LocalContext.current

    ConfirmAlertDialog(
        onDismissRequest = onDismissRequest,
        onConfirm = {
            if (cameraPermissionState.hasPermanentlyDenied()) {
                context.startActivity(
                    Intent(
                        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                        Uri.fromParts("package", context.packageName, null)
                    )
                )
            } else if (cameraPermissionState.shouldShowRationale) {
                cameraPermissionState.launchPermissionRequest()
            }
            onConfirm()
        },
        titleText = stringResource(R.string.camera_permission_title),
        messageText = stringResource(R.string.camera_permission_text),
        cancelButtonText = stringResource(R.string.button_maybe_later)
    )
}

@Composable
fun ConfirmAlertDialog(
    onDismissRequest: () -> Unit,
    onConfirm: () -> Unit,
    titleText: String,
    messageText: String,
    confirmButtonText: String = stringResource(R.string.button_ok),
    cancelButtonText: String = stringResource(R.string.button_cancel)
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