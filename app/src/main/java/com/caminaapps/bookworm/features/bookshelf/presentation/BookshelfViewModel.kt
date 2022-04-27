package com.caminaapps.bookworm.features.bookshelf.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.caminaapps.bookworm.core.domain.model.Book
import com.caminaapps.bookworm.core.domain.model.UserMessage
import com.caminaapps.bookworm.features.bookshelf.domain.GetBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class BookshelfViewModel @Inject constructor(
    private val getBooks: GetBooksUseCase
) : ViewModel() {

    var uiState by mutableStateOf(BookshelfUiState())
        private set

    init {
        viewModelScope.launch {
            getBooks().collect { bookList ->
                uiState = uiState.copy(books = bookList)
            }
        }
    }
}

data class BookshelfUiState(
    val books: List<Book> = emptyList(),
    val userMessages: List<UserMessage> = emptyList()
)