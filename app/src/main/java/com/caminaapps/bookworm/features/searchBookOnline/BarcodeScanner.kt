package com.caminaapps.bookworm.features.searchBookOnline

import android.content.Context
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning
import timber.log.Timber
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class BarcodeScanner(private val context: Context) {

    private val options = GmsBarcodeScannerOptions.Builder()
        .setBarcodeFormats(
            Barcode.FORMAT_EAN_8,
            Barcode.FORMAT_EAN_13
        )
        .build()

    suspend fun scanBarcode(): String? = suspendCoroutine { continuation ->
        val scanner = GmsBarcodeScanning.getClient(context, options)
        scanner.startScan()
            .addOnSuccessListener { barcode ->
                val encodedBarcode = barcode.rawValue
                Timber.i("barcode: $encodedBarcode")
                continuation.resume(encodedBarcode)
            }
            .addOnCanceledListener {
                continuation.resume(null)
            }
            .addOnFailureListener { e ->
                Timber.e(e)
                continuation.resume(null)
            }
    }
}
