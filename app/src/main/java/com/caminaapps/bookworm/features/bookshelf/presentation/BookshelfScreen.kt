package com.caminaapps.bookworm.presentation.screens.bookshelf

import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import com.caminaapps.bookworm.presentation.screens.bookshelf.components.AddBookFloatingActionButton
import com.google.accompanist.permissions.ExperimentalPermissionsApi

@ExperimentalComposeUiApi
@ExperimentalPermissionsApi
@Composable
fun BookshelfScreen(
    onScanBarcode: () -> Unit,
    viewModel: BookshelfViewModel = hiltViewModel()
) {
    var openCameraPreview by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            AddBookFloatingActionButton(
                onManual = { /*TODO*/ },
                onScan = onScanBarcode
            )
        }
    ) {

        Text("- Bookshelf -")

//        if (openCameraPreview) {
//            Dialog(
//                onDismissRequest = { openCameraPreview = false },
//                properties = DialogProperties(
//                    dismissOnBackPress = true,
//                    dismissOnClickOutside = false,
//                    usePlatformDefaultWidth = false
//                )
//            ) {
//                CameraPreview(onBarcodeDetected = { barcode ->
//                    Timber.d("barcode: $barcode")
//                    showISBNSearchResult(barcode)
//                    openCameraPreview = false
//                })
//            }
//        }

    }
}




