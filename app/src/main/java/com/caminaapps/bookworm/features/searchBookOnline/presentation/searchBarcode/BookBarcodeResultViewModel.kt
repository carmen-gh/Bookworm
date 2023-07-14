package com.caminaapps.bookworm.features.searchBookOnline.presentation.searchBarcode

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.caminaapps.bookworm.core.model.Book
import com.caminaapps.bookworm.features.searchBookOnline.domain.SaveBookFromOnlineSearchUseCase
import com.caminaapps.bookworm.features.searchBookOnline.domain.SearchBookByIsbnUseCase
import com.caminaapps.bookworm.features.searchBookOnline.navigation.SearchBookByIsbnArgs
import com.caminaapps.bookworm.util.createExceptionHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class BookBarcodeResultViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val searchBook: SearchBookByIsbnUseCase,
    private val saveBook: SaveBookFromOnlineSearchUseCase,
) : ViewModel() {

    var uiState by mutableStateOf(SearchBookIsbnUiState())
        private set

    private val searchArgs: SearchBookByIsbnArgs = SearchBookByIsbnArgs(savedStateHandle)
    val isbn = searchArgs.isbn

    init {
        loadBook(isbn)
    }

    private fun loadBook(isbn: String) {
        Timber.d("loading book with isbn: $isbn")
        val errorMessage = "Failed to fetch book info"
        val exceptionHandler = viewModelScope.createExceptionHandler(errorMessage) { onFailure(it) }

        viewModelScope.launch(exceptionHandler) {
            uiState = SearchBookIsbnUiState(isLoading = true)
            val book = searchBook(isbn)
            uiState = SearchBookIsbnUiState(isLoading = false, book = book)
            Timber.d("found book ...")
        }
    }

    private fun onFailure(e: Throwable) {
//        TODO("proper error message to the user")
        Timber.e(e.localizedMessage)
        uiState = SearchBookIsbnUiState(isLoading = false, errorOccurred = true)
    }

    fun saveBook() {
        val book = uiState.book ?: return

        Timber.d("save book ...")
        val errorMessage = "Failed to save book"
        val exceptionHandler = viewModelScope.createExceptionHandler(errorMessage) { onFailure(it) }

        viewModelScope.launch(exceptionHandler) {
            uiState = uiState.copy(isLoading = true)
            saveBook(book)
            uiState = uiState.copy(isLoading = false)
        }
    }
}

data class SearchBookIsbnUiState(
    val isLoading: Boolean = false,
    val book: Book? = null,
    val errorOccurred: Boolean = false,
)
