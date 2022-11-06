package com.caminaapps.bookworm.features.bookshelf.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.caminaapps.bookworm.core.model.Book
import com.caminaapps.bookworm.core.model.BookshelfSortOrder
import com.caminaapps.bookworm.features.bookshelf.domain.GetAllBooksUseCase
import com.caminaapps.bookworm.features.bookshelf.domain.GetBookshelfSortOrderUseCase
import com.caminaapps.bookworm.features.bookshelf.domain.UpdateBookshelfSortOrderUseCase
import com.caminaapps.bookworm.util.Result
import com.caminaapps.bookworm.util.asResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookshelfViewModel @Inject constructor(
    private val getAllBooks: GetAllBooksUseCase,
    private val getBookshelfSortOrder: GetBookshelfSortOrderUseCase,
    private val updateBookshelfSortOrder: UpdateBookshelfSortOrderUseCase
) : ViewModel() {

    val uiState: StateFlow<BookshelfUiState> = uiStream()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = BookshelfUiState.Loading
        )

    private fun uiStream(): Flow<BookshelfUiState> {
        return combine(getAllBooks(), getBookshelfSortOrder(), ::Pair)
            .asResult()
            .map { result ->
                when (result) {
                    is Result.Success -> {
                        val (books, sortOrder) = result.data
                        BookshelfUiState.Success(books, sortOrder)
                    }

                    is Result.Loading -> BookshelfUiState.Loading
                    is Result.Error -> BookshelfUiState.Error
                }
            }
    }

    fun updateSortOrder(sortOrder: BookshelfSortOrder) {
        viewModelScope.launch {
            updateBookshelfSortOrder(sortOrder)
        }
    }

//     fun onItemDelet(id: String) {
//         viewModelScope.launch {
//             deleteBookUseCase(id)
//         }
//     }
}

sealed interface BookshelfUiState {
    data class Success(val books: List<Book>, val sortOrder: BookshelfSortOrder) : BookshelfUiState
    object Error : BookshelfUiState
    object Loading : BookshelfUiState
}
