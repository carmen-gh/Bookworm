package com.caminaapps.bookworm.presentation.screens.bookshelf

import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.caminaapps.bookworm.presentation.components.CameraPreview
import com.caminaapps.bookworm.presentation.screens.bookshelf.components.AddBookFloatingActionButton
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import timber.log.Timber

@ExperimentalComposeUiApi
@ExperimentalPermissionsApi
@Composable
fun BookshelfScreen(
    viewModel: BookshelfViewModel
) {
    var openCameraPreview by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            AddBookFloatingActionButton(
                onManual = { /*TODO*/ },
                onScan = { openCameraPreview = true }
            )
        }
    ) {

        Text("- Bookshelf -")

        if (openCameraPreview) {
            Dialog(
                onDismissRequest = { openCameraPreview = false },
                properties = DialogProperties(
                    dismissOnBackPress = true,
                    dismissOnClickOutside = false,
                    usePlatformDefaultWidth = false
                )
            ) {
                CameraPreview(onBarcodeDetected = { barcode ->
                    Timber.d(barcode)
                    openCameraPreview = false
                })
            }
        }

    }
}




