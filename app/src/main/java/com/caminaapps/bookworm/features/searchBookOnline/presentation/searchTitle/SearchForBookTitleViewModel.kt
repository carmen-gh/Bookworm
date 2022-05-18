package com.caminaapps.bookworm.features.searchBookOnline.presentation.searchTitle

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.caminaapps.bookworm.core.domain.model.Book
import com.caminaapps.bookworm.core.domain.model.UserMessage
import com.caminaapps.bookworm.features.searchBookOnline.domain.SaveBookFromOnlineSearchUseCase
import com.caminaapps.bookworm.features.searchBookOnline.domain.SearchBookByTitleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchForBookTitleViewModel @Inject constructor(
    private val searchBook: SearchBookByTitleUseCase,
    private val saveBook: SaveBookFromOnlineSearchUseCase
) : ViewModel() {

    var uiState by mutableStateOf<SearchForBookTitleUiState>(SearchForBookTitleUiState())
        private set

    fun search(query: String) {
        if (query.isBlank()) {
            uiState = SearchForBookTitleUiState()
            return
        }

        viewModelScope.launch {
            uiState = SearchForBookTitleUiState(isLoading = true)
            // (TODO) add error handling
            val result = searchBook(query)
            uiState = SearchForBookTitleUiState(isLoading = false, results = result)
        }
    }

    fun onAddBook(book: Book) {
        viewModelScope.launch {
            saveBook(book)
        }
    }
}

// results null no search, if empty no matching results

data class SearchForBookTitleUiState(
    val query: String = "", // ?
    val isLoading: Boolean = false,
    val results: List<Book>? = null,
    val userMessages: List<UserMessage> = emptyList(),
)

//
//sealed class SearchForBookTitleUiState {
//    object Initial : SearchForBookTitleUiState()
//    object Loading : SearchForBookTitleUiState()
//    data class SearchResults(val books: List<Book>) :  SearchForBookTitleUiState()
//    data class Error(@StringRes val errorMessageId: Int = 0) : SearchForBookTitleUiState()
//}