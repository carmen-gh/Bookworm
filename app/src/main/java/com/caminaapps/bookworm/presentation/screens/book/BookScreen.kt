package com.caminaapps.bookworm.presentation.screens.book

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.caminaapps.bookworm.presentation.components.TopAppBarNavigationUp

@Composable
fun BookScreen(
    viewModel: BookViewModel,
    onUpNavigationClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBarNavigationUp(
                title = "Book details",
                onClick = onUpNavigationClick
            )
        },
    ) {
        // check for ui state

        BookContent()
    }
}


@Composable
fun BookContent() {

}