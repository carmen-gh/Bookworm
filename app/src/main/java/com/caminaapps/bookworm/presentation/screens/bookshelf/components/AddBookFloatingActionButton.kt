package com.caminaapps.bookworm.presentation.screens.bookshelf.components


import android.Manifest
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material.FabPosition
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.PhotoCamera
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.caminaapps.bookworm.R
import com.caminaapps.bookworm.presentation.components.CameraPermissionAlertDialog
import com.caminaapps.bookworm.presentation.components.SpeedDialFloatingActionButton
import com.caminaapps.bookworm.presentation.components.SpeedDialItem
import com.caminaapps.bookworm.presentation.theme.BookwormTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import timber.log.Timber


@ExperimentalPermissionsApi
@Composable
fun AddBookFloatingActionButton(
    onManual: () -> Unit,
    onScan: () -> Unit
) {
    SpeedDialFloatingActionButton(
        icon = {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = stringResource(id = R.string.button_add)
            )
        }
    ) {
        SpeedDialItem(onClick = onManual) {
            Icon(
                imageVector = Icons.Outlined.Edit,
                contentDescription = stringResource(id = R.string.button_edit)
            )
        }
        CameraSpeedDialItem(onScan = onScan)
    }
}

@ExperimentalPermissionsApi
@Composable
fun ColumnScope.CameraSpeedDialItem(onScan: () -> Unit) {
    val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA)
    var openDialog by remember { mutableStateOf(false) }

    SpeedDialItem(onClick = {
        if (!cameraPermissionState.permissionRequested) {
            cameraPermissionState.launchPermissionRequest()
        } else if (!cameraPermissionState.hasPermission) {
            openDialog = true
        } else if (cameraPermissionState.hasPermission) {
            Timber.d("start camera")
            onScan()
        }
    }) {
        Icon(
            imageVector = Icons.Outlined.PhotoCamera,
            contentDescription = stringResource(id = R.string.button_scan)
        )
    }

    if (openDialog) {
        CameraPermissionAlertDialog(
            onDismissRequest = { openDialog = false },
            onConfirm = {},
            cameraPermissionState = cameraPermissionState
        )
    }
}


@ExperimentalPermissionsApi
@Preview(showBackground = true, name = "add floating button")
@Composable
fun PreviewAddFloatingButton() {
    BookwormTheme {
        Scaffold(
            floatingActionButtonPosition = FabPosition.End,
            floatingActionButton = {
                AddBookFloatingActionButton(
                    onManual = { /*TODO*/ },
                    onScan = {  /*TODO*/ }
                )
            }
        ) { }
    }
}