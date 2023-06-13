package com.caminaapps.bookworm.features.searchBookOnline.presentation.searchBarcode

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.caminaapps.bookworm.features.searchBookOnline.BarcodeScanner
import timber.log.Timber

@Composable
fun BarcodeScannerLauncher(
    onBarcodeDetection: (String) -> Unit,
    onCancel: () -> Unit,
) {
    val localContext = LocalContext.current
    val barcodeScanner = remember { BarcodeScanner(localContext) }

    LaunchedEffect(true) {
        Timber.i("Start scan barcode")
        val result = barcodeScanner.scanBarcode()
        if (result != null) {
            onBarcodeDetection(result)
        } else {
            onCancel()
        }
    }
}
