package com.caminaapps.bookworm.features.searchBookOnline.presentation.barcodeScanner

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.caminaapps.bookworm.R
import com.caminaapps.bookworm.core.ui.component.TopAppBarNavigationClose


@Composable
fun CameraScreen(
    onClose: () -> Unit,
    onBarcodeDetected: (isbn: String) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBarNavigationClose(
                title = stringResource(id = R.string.screen_title_scan_barcode),
                onClick = onClose
            )
        }
    ) {
        CameraPreview(onBarcodeDetected = onBarcodeDetected)
    }
}
