package com.caminaapps.bookworm.features.searchBookOnline.presentation.searchTitle

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.caminaapps.bookworm.core.model.Book
import com.caminaapps.bookworm.features.searchBookOnline.domain.SaveBookFromOnlineSearchUseCase
import com.caminaapps.bookworm.features.searchBookOnline.domain.SearchBookByTitleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchBookTitleViewModel @Inject constructor(
    private val searchBook: SearchBookByTitleUseCase,
    private val saveBook: SaveBookFromOnlineSearchUseCase
) : ViewModel() {

    suspend fun search(query: String): List<Book> =
        searchBook(query)

    fun onAddBook(book: Book) {
        viewModelScope.launch {
            saveBook(book)
        }
    }
}
