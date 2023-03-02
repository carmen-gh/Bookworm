package com.caminaapps.bookworm.features.bookshelf.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.caminaapps.bookworm.core.model.Book
import com.caminaapps.bookworm.core.model.BookId
import com.caminaapps.bookworm.core.ui.component.TopAppBarSlotNavigationUp
import com.caminaapps.bookworm.core.ui.component.TrackedScreen
import com.caminaapps.bookworm.features.bookshelf.presentation.components.BookList
import com.caminaapps.bookworm.features.searchBookOnline.presentation.searchTitle.SimpleSearchBar

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BookshelfSearchScreen(
    onUpNavigationClick: () -> Unit,
    onSearchResultSelection: (id: BookId) -> Unit,
    viewModel: BookshelfSearchViewModel = hiltViewModel(),
) {
    TrackedScreen(name = "Bookshelf Search")

    val searchResults: List<Book> by viewModel.searchResults.collectAsStateWithLifecycle()

    BookshelfSearchContent(
        onSearchQueryChange = { viewModel.onSearch(it) },
        results = searchResults,
        onResultSelection = { onSearchResultSelection(it) },
        onUpNavigationClick = { onUpNavigationClick() }
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BookshelfSearchContent(
    onSearchQueryChange: (query: String) -> Unit,
    results: List<Book>,
    onResultSelection: (id: BookId) -> Unit,
    onUpNavigationClick: () -> Unit
) {
    var query by rememberSaveable { mutableStateOf("") }

    Scaffold(topBar = {
        TopAppBarSlotNavigationUp(
            title = {
                SimpleSearchBar(
                    modifier = Modifier.padding(vertical = 4.dp),
                    onValueChange = {
                        query = it
                        onSearchQueryChange(it)
                    },
                    value = query,
                    onSearchKeyboardAction = { },
                    onResetValue = {
                        query = ""
                    }
                )
            },
            onClick = { onUpNavigationClick() }
        )
    }) { innerPadding ->
        BookList(
            books = results,
            onItemClick = { onResultSelection(it.id) },
            modifier = Modifier.padding(innerPadding)
        )
    }
}
