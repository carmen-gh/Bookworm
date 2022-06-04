package com.caminaapps.bookworm.features.searchBookOnline.presentation.searchTitle

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.caminaapps.bookworm.core.model.Book


// change to Search(..) and create an outer composable Screen
//
//  screen TopAppBar, onBook, onAdd, onSearch


@Composable
fun SearchBookTitleScreen(
    onBookClick: (Book) -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    state: SearchState = rememberSearchState(),
    viewModel: SearchBookTitleViewModel = hiltViewModel()
) {

    val focusManager = LocalFocusManager.current

    Surface(modifier = modifier.fillMaxSize()) {
        Column {
            Spacer(modifier = Modifier.padding(5.dp))

            SearchBar(
                query = state.query,
                onQueryChange = {
                    state.query = it
                    state.searchResults = null
                },
                onSearchKeyboardAction = {
                    focusManager.clearFocus()
                    state.startSearchAction = true
                },
                searchFocused = state.focused,
                onSearchFocusChange = { state.focused = it },
                onClearQuery = {
                    state.query = TextFieldValue("")
                    state.searchResults = null
                },
                searching = state.searching
            )

            Divider()

            LaunchedEffect(state.startSearchAction) {
                if (!state.startSearchAction) return@LaunchedEffect
                state.searching = true
                state.searchResults = viewModel.search(state.query.text)
                state.searching = false
                state.startSearchAction = false
            }

            when (state.searchDisplay) {
                SearchDisplay.Results -> SearchResults(
                    searchResults = state.searchResults ?: emptyList(),
//                    onBookClick = {
//                        focusManager.clearFocus()
//                        onBookClick(it)
//                    },
                    onAddClick = {
                        focusManager.clearFocus()
                        viewModel.onAddBook(it)
                        onNavigateUp()
                    }
                )
                SearchDisplay.NoResults -> NoResults(state.query.text)
                SearchDisplay.Empty -> Text("search on Open Library")
            }
        }
    }
}
