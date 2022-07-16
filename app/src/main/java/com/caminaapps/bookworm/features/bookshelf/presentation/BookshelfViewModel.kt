package com.caminaapps.bookworm.features.bookshelf.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.caminaapps.bookworm.core.model.Book
import com.caminaapps.bookworm.core.model.UserMessage
import com.caminaapps.bookworm.features.bookshelf.domain.GetAllBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class BookshelfViewModel @Inject constructor(
    private val getAllBooks: GetAllBooksUseCase,
    // private val deleteBookUseCase: DeleteBookUseCase
) : ViewModel() {

    var uiState by mutableStateOf(BookshelfUiState())
        private set

    init {
        viewModelScope.launch {
            getAllBooks().collect { bookList ->
                Timber.d("update ui state books")
                uiState = uiState.copy(books = bookList)
            }
        }
    }

    // fun onItemDelet(id: String) {
    //     viewModelScope.launch {
    //         deleteBookUseCase(id)
    //     }
    // }
}

data class BookshelfUiState(
    val books: List<Book> = emptyList(),
    val userMessages: List<UserMessage> = emptyList()
)
