package com.caminaapps.bookworm.features.enterBook

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EnterBookViewModel @Inject constructor(
    private val saveBookUseCase: SaveEnteredBookUseCase,
) : ViewModel() {

    fun saveBook(
        title: String,
        subtitle: String,
        author: String,
        published: String,
        finihedReading: Boolean,
        isFavourite: Boolean
    ) {
        // expose ui state

        viewModelScope.launch {
            saveBookUseCase(
                title = title,
                subtitle = subtitle,
                author = author,
                published = published,
                isFavourite = isFavourite,
                finishedReading = finihedReading
            )
        }
    }
}

sealed interface EnterBookUiState {
    object EnterBookInfo : EnterBookUiState
    object BookSaved : EnterBookUiState
}
