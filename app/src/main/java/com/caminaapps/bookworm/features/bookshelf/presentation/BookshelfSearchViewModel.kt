package com.caminaapps.bookworm.features.bookshelf.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.caminaapps.bookworm.core.model.Book
import com.caminaapps.bookworm.features.bookshelf.domain.SearchBookshelfUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class BookshelfSearchViewModel @Inject constructor(
    private val searchBookshelfUseCase: SearchBookshelfUseCase
) : ViewModel() {

    private val searchDelayMillis: Long = 500
    private val searchText = MutableStateFlow("")
    private val _searchResult = MutableStateFlow<List<Book>>(emptyList())
    val searchResults = _searchResult.asStateFlow()

    init {
        viewModelScope.launch {
            searchText
                .debounce(searchDelayMillis)
                .distinctUntilChanged()
                .collect {
                    _searchResult.value = searchBookshelfUseCase(it)
                }
        }
    }

    fun onSearch(query: String) {
        searchText.value = query
    }
}
