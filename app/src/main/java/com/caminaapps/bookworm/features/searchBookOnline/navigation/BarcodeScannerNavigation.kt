package com.caminaapps.bookworm.features.searchBookOnline.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.caminaapps.bookworm.features.searchBookOnline.presentation.searchBarcode.BarcodeScannerEffect

const val BARCODE_SCANNER_ROUTE = "barcode_scanner_route"

fun NavController.navigateToBarcodeScanner(navOptions: NavOptions? = null) {
    this.navigate(BARCODE_SCANNER_ROUTE, navOptions)
}

fun NavGraphBuilder.barcodeScannerDestination(
    onBarcodeDetection: (String) -> Unit,
    onCancel: () -> Unit,
) {
    composable(route = BARCODE_SCANNER_ROUTE) {
        BarcodeScannerEffect(onBarcodeDetection = onBarcodeDetection, onCancel = onCancel)
    }
}
