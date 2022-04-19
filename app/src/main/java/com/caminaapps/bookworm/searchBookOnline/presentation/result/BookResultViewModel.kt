package com.caminaapps.bookworm.searchBookOnline.presentation.result

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.caminaapps.bookworm.domain.model.Book
import com.caminaapps.bookworm.domain.usecase.bookshelf.AddBookUseCase
import com.caminaapps.bookworm.presentation.navigation.Screen
import com.caminaapps.bookworm.searchBookOnline.domain.SearchBookByIsbnUseCase
import com.caminaapps.bookworm.util.createExceptionHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class BookResultViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val searchBook: SearchBookByIsbnUseCase,
    private val addBook: AddBookUseCase
) : ViewModel() {

    var uiState by mutableStateOf(SearchBookIsbnUiState())
        private set

    init {
        savedStateHandle.get<String>(Screen.SearchIsbnBookResult.argumentKey)?.let { isbn ->
            loadBook(isbn)
        }
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
        // do something
        uiState = SearchBookIsbnUiState(isLoading = false, errorOcured = true)
    }

    fun saveBook() {
        Timber.d("save book ...")
//        uiState.book?.let {
//            addBook(it)
//        }
    }
}

data class SearchBookIsbnUiState(
    val isLoading: Boolean = false,
    val book: Book? = null,
    val errorOcured: Boolean = false
)