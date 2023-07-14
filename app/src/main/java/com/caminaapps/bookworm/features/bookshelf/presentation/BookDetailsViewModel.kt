package com.caminaapps.bookworm.features.bookshelf.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.caminaapps.bookworm.core.model.Book
import com.caminaapps.bookworm.features.bookshelf.domain.DeleteBookUseCase
import com.caminaapps.bookworm.features.bookshelf.domain.GetBookDetailsUseCase
import com.caminaapps.bookworm.features.bookshelf.navigation.BookDetailsArgs
import com.caminaapps.bookworm.util.AsyncResult
import com.caminaapps.bookworm.util.AsyncResult.Failure
import com.caminaapps.bookworm.util.AsyncResult.Loading
import com.caminaapps.bookworm.util.AsyncResult.Success
import com.caminaapps.bookworm.util.WhileUiSubscribed
import com.caminaapps.bookworm.util.asAsyncResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getBookDetails: GetBookDetailsUseCase,
    private val deleteBook: DeleteBookUseCase,
) : ViewModel() {

    private val bookDetailsArgs: BookDetailsArgs = BookDetailsArgs(savedStateHandle)
    val bookId = bookDetailsArgs.bookId

    private val bookStream: Flow<AsyncResult<Book?>> = getBookDetails(bookId).asAsyncResult()

    val uiState: StateFlow<BookDetailsUiState> = bookStream.map { result ->
        when (result) {
            is Success -> result.data?.let { BookDetailsUiState.Success(it) }
                ?: BookDetailsUiState.NotFound

            is Loading -> BookDetailsUiState.Loading
            is Failure -> BookDetailsUiState.Error
        }
    }.stateIn(
        scope = viewModelScope,
        started = WhileUiSubscribed,
        initialValue = BookDetailsUiState.Loading
    )

    fun onDeleteBook(bookId: String) {
        viewModelScope.launch {
            deleteBook(bookId)
        }
    }
}

sealed interface BookDetailsUiState {
    data class Success(val book: Book) : BookDetailsUiState
    object NotFound : BookDetailsUiState
    object Error : BookDetailsUiState
    object Loading : BookDetailsUiState
}
