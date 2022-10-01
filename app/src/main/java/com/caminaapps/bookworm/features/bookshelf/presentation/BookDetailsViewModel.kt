package com.caminaapps.bookworm.features.bookshelf.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.caminaapps.bookworm.core.model.Book
import com.caminaapps.bookworm.features.bookshelf.domain.DeleteBookUseCase
import com.caminaapps.bookworm.features.bookshelf.domain.GetBookDetailsUseCase
import com.caminaapps.bookworm.util.Result
import com.caminaapps.bookworm.util.Result.Error
import com.caminaapps.bookworm.util.Result.Loading
import com.caminaapps.bookworm.util.Result.Success
import com.caminaapps.bookworm.util.asResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getBookDetails: GetBookDetailsUseCase,
    private val deleteBook: DeleteBookUseCase
) : ViewModel() {

    private val bookId: String = checkNotNull(savedStateHandle["bookId"])
    private val bookStream: Flow<Result<Book?>> = getBookDetails(bookId).asResult()

    val uiState: StateFlow<BookDetailsUiState> = bookStream.map { result ->
        when (result) {
            is Success -> result.data?.let { BookDetailsUiState.Success(it) }
                ?: BookDetailsUiState.NotFound

            is Loading -> BookDetailsUiState.Loading
            is Error -> BookDetailsUiState.Error
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
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
