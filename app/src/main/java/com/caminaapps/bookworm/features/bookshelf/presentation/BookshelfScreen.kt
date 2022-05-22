package com.caminaapps.bookworm.features.bookshelf.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.caminaapps.bookworm.core.domain.model.Book
import com.caminaapps.bookworm.features.bookshelf.presentation.components.AddBookFloatingActionButton
import com.caminaapps.bookworm.presentation.screens.bookshelf.components.BookList
import com.google.accompanist.permissions.ExperimentalPermissionsApi

@OptIn(ExperimentalMaterialApi::class)
@ExperimentalComposeUiApi
@ExperimentalPermissionsApi
@Composable
fun BookshelfScreen(
    onScanBarcode: () -> Unit,
    onBookClick: (id: Book) -> Unit,
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
    ) { innerPadding ->

        if (viewModel.uiState.books.isNotEmpty()) {
            BookList(
                modifier = Modifier.padding(innerPadding),
                books = viewModel.uiState.books,
                onItemClick = onBookClick
            )
        } else {
            Text("add some books to get started")
        }
    }

}
