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
import com.caminaapps.bookworm.core.model.Book
import com.caminaapps.bookworm.features.bookshelf.presentation.components.AddBookFloatingActionButton
import com.caminaapps.bookworm.features.bookshelf.presentation.components.BookList
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import timber.log.Timber

@OptIn(ExperimentalMaterialApi::class)
@ExperimentalComposeUiApi
@ExperimentalPermissionsApi
@Composable
fun BookshelfScreen(
    onScanBarcode: () -> Unit,
    onSearchOnline: () -> Unit,
    onBookClick: (id: Book) -> Unit,
    viewModel: BookshelfViewModel = hiltViewModel()
) {

    Scaffold(
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            AddBookFloatingActionButton(
                onManual = { /*TODO*/ },
                onSearch = onSearchOnline,
                onScan = onScanBarcode
            )
        }
    ) { innerPadding ->

        if (viewModel.uiState.books.isNotEmpty()) {
            Timber.d("books count: ${viewModel.uiState.books.count()}")
            BookList(
                modifier = Modifier.padding(innerPadding),
                books = viewModel.uiState.books,
                onItemClick = onBookClick,
                onItemDelete = { book ->
                    viewModel.onItemDelet(book.id)
                }
            )
        } else {
            Text("add some books to get started")
        }
    }

}
