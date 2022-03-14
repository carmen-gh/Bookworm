package com.caminaapps.bookworm.presentation.screens.bookshelf

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun BookshelfScreen(
    viewModel: BookshelfViewModel
) {
    if(viewModel.uiState.isLoading) {
        Text("- Bookshelf loading -")
    }

    Text("- Bookshelf -")
}