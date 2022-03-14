package com.caminaapps.bookworm.presentation.screens.book

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.caminaapps.bookworm.domain.model.Book
import com.caminaapps.bookworm.domain.model.Message
import com.caminaapps.bookworm.presentation.navigation.Screen
import com.caminaapps.bookworm.presentation.screens.bookshelf.BookshelfUiState
import com.caminaapps.bookworm.presentation.screens.settings.SettingsViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    var uiState by mutableStateOf(BookUiState())
        private set


    init {
        savedStateHandle.get<String>(Screen.Book.argumentKey)?.let {
            // call use case
        }
    }

}

data class BookUiState(
    val loading: Boolean = false,
    val book: Book? = null,
    val userMessages: List<Message> = emptyList(),
)