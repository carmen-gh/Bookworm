package com.caminaapps.bookworm.features.searchBookOnline.presentation.searchTitle

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import com.caminaapps.bookworm.core.model.Book

enum class SearchDisplay {
    Results, NoResults
}

@Stable
class SearchState(
    query: TextFieldValue,
    focused: Boolean,
    startSearchAction: Boolean,
    searching: Boolean,
    searchResults: List<Book>
) {
    var query by mutableStateOf(query)
    var focused by mutableStateOf(focused)
    var startSearchAction by mutableStateOf(startSearchAction)
    var searching by mutableStateOf(searching)
    var searchResults by mutableStateOf(searchResults)

    val searchDisplay: SearchDisplay
        get() = when {
            searchResults.isEmpty() -> SearchDisplay.NoResults
            else -> SearchDisplay.Results
        }
}

@Composable
fun rememberSearchState(
    query: TextFieldValue = TextFieldValue(""),
    focused: Boolean = false,
    startSearchAction: Boolean = false,
    searching: Boolean = false,
    searchResults: List<Book> = emptyList()
): SearchState {
    return remember {
        SearchState(
            query = query,
            focused = focused,
            startSearchAction = startSearchAction,
            searching = searching,
            searchResults = searchResults
        )
    }
}
