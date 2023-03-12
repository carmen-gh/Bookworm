package com.caminaapps.bookworm.features.bookshelf.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.caminaapps.bookworm.core.model.Book
import com.caminaapps.bookworm.features.bookshelf.domain.SearchBookshelfUseCase
import com.caminaapps.bookworm.util.WhileUiSubscribed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class BookshelfSearchViewModel @Inject constructor(
    private val searchBookshelfUseCase: SearchBookshelfUseCase
) : ViewModel() {

    private val searchDelayMillis: Long = 500
    private val searchText = MutableStateFlow("")
    private val _searchResult = MutableStateFlow<List<Book>>(emptyList())
    val searchResults = _searchResult.stateIn(
        scope = viewModelScope,
        started = WhileUiSubscribed,
        initialValue = emptyList()
    )

    init {
        searchText
            .debounce(searchDelayMillis)
            .distinctUntilChanged()
            .onEach {
                _searchResult.value = searchBookshelfUseCase(it)
            }.launchIn(viewModelScope)
    }

    fun onSearch(query: String) {
        searchText.value = query
    }
}
