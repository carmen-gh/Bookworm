package com.caminaapps.bookworm.features.bookshelf.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.caminaapps.bookworm.core.domain.model.Book
import com.caminaapps.bookworm.core.domain.model.UserMessage
import com.caminaapps.bookworm.core.presentation.navigation.Screen
import com.caminaapps.bookworm.features.bookshelf.domain.GetBookDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getBookDetails: GetBookDetailsUseCase
) : ViewModel() {

    var uiState by mutableStateOf(BookUiState())
        private set

    init {
        Screen.BookDetail.argumentKey?.let { argumentKey ->
            savedStateHandle.get<String>(argumentKey)?.let { id ->
                loadBookDetails(id)
            }
        }
    }

    private fun loadBookDetails(id: String) {
        viewModelScope.launch {
            getBookDetails(id).collect { book ->
                uiState = uiState.copy(book = book)
            }
        }
    }

}

data class BookUiState(
    val book: Book? = null,
    val userMessages: List<UserMessage> = emptyList(),
)