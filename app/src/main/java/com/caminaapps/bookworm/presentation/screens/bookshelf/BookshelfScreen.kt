package com.caminaapps.bookworm.presentation.screens.bookshelf

import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun BookshelfScreen(
    viewModel: BookshelfViewModel
) {
    Scaffold(
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            AddBookFloatingActionButton(
                onManual = { /*TODO*/ },
                onScan = {  /*TODO*/ }
            )
        }
    ) {
        Text("- Bookshelf -")
    }
//    if(viewModel.uiState.isLoading) {
//        Text("- Bookshelf loading -")
//    }
}

