package com.caminaapps.bookworm.features.searchBookOnline.presentation.searchTitle

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.caminaapps.bookworm.core.model.Book
import com.caminaapps.bookworm.features.searchBookOnline.domain.SaveBookFromOnlineSearchUseCase
import com.caminaapps.bookworm.features.searchBookOnline.domain.SearchBookByTitleUseCase
import com.caminaapps.bookworm.features.searchBookOnline.presentation.searchTitle.SearchForBookTitleUiState.*
import com.caminaapps.bookworm.util.createExceptionHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchForBookTitleViewModel @Inject constructor(
    private val searchBook: SearchBookByTitleUseCase,
    private val saveBook: SaveBookFromOnlineSearchUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<SearchForBookTitleUiState>(Empty)
    val uiState: StateFlow<SearchForBookTitleUiState> = _uiState

    fun search(query: String) {
        if (query.isBlank()) {
            _uiState.value = Empty
            return
        }

        val errorMessage = "Failed to search for book title"
        val exceptionHandler = viewModelScope.createExceptionHandler(errorMessage) { onFailure(it) }

        viewModelScope.launch(exceptionHandler) {
            _uiState.value = Loading
            val result = searchBook(query)
            _uiState.value = when (result.isEmpty()) {
                true -> NoResults
                false -> Success(books = result)
            }
        }
    }

    fun onAddBook(book: Book) {
        viewModelScope.launch {
            saveBook(book)
        }
    }

    private fun onFailure(e: Throwable) {
        _uiState.value = Error
    }
}


sealed interface SearchForBookTitleUiState {
    object Empty : SearchForBookTitleUiState
    object Loading : SearchForBookTitleUiState
    data class Success(val books: List<Book>) : SearchForBookTitleUiState
    object NoResults : SearchForBookTitleUiState
    object Error : SearchForBookTitleUiState
}
// data class Error(@StringRes val errorMessageId: Int = 0) : SearchForBookTitleUiState()
