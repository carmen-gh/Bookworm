package com.caminaapps.bookworm.presentation.screens.bookshelf

import android.Manifest
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState

@ExperimentalPermissionsApi
@Composable
fun BookshelfScreen(
    viewModel: BookshelfViewModel
) {
    Scaffold(
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA)
            AddBookFloatingActionButton(
                onManual = { /*TODO*/ },
                onScan = { cameraPermissionState.launchPermissionRequest() }
            )
        }
    ) {
        Text("- Bookshelf -")
    }

}

