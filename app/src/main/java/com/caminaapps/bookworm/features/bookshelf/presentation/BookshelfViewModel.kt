package com.caminaapps.bookworm.presentation.screens.bookshelf

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.caminaapps.bookworm.core.domain.model.Book
import com.caminaapps.bookworm.core.domain.model.UserMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject


@HiltViewModel
class BookshelfViewModel @Inject constructor(

) : ViewModel() {

    var uiState by mutableStateOf(BookshelfUiState(isLoading = true))
        private set

    private var fetchJob: Job? = null

    fun fetchBooks(category: String) {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            try {
//                val newsItems = repository.newsItemsForCategory(category)
//                uiState = uiState.copy(newsItems = newsItems)
            } catch (ioe: IOException) {
                // Handle the error and notify the UI when appropriate.
//                val messages = getMessagesFromThrowable(ioe)
//                uiState = uiState.copy(userMessages = messages)
            }
        }
    }

}

data class BookshelfUiState(
    val isLoading: Boolean = false,
    val books: List<Book> = emptyList(),
    val userMessages: List<UserMessage> = emptyList()
)