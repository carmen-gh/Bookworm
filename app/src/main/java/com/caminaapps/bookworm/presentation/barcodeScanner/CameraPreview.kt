package com.caminaapps.bookworm.presentation.barcodeScanner

import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat


@Composable
fun CameraPreview(
    modifier: Modifier = Modifier,
    cameraSelector: CameraSelector = CameraSelector.DEFAULT_BACK_CAMERA,
    onBarcodeDetected: (String) -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val localContext = LocalContext.current
    val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(localContext) }

    AndroidView(
        modifier = modifier.fillMaxSize(),
        factory = { context ->
            val previewView = PreviewView(context)
            val executor = ContextCompat.getMainExecutor(context)

            cameraProviderFuture.addListener({
                val cameraProvider = cameraProviderFuture.get()
                val preview = Preview.Builder().build().also {
                    it.setSurfaceProvider(previewView.surfaceProvider)
                }
                val imageAnalyzer = ImageAnalysis.Builder()
                    .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                    .build()
                    .also { imageAnalysis ->
                        imageAnalysis.setAnalyzer(
                            executor, BarcodeAnalyzer(onBarcodeDetected = { barcodes ->
                                barcodes.first().displayValue?.let { barcode ->
                                    onBarcodeDetected(barcode)
                                }
                            })
                        )
                    }

                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    lifecycleOwner,
                    cameraSelector,
                    preview,
                    imageAnalyzer
                )
            }, executor)
            previewView
        },
        update = {},
    )
}