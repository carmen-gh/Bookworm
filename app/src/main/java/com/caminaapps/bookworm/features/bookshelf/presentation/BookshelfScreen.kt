package com.caminaapps.bookworm.features.bookshelf.presentation

import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
    }
    
}




