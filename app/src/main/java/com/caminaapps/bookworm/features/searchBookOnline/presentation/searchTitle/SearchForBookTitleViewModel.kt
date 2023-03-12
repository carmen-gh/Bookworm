package com.caminaapps.bookworm.features.searchBookOnline.presentation.searchTitle

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.caminaapps.bookworm.R
import com.caminaapps.bookworm.core.model.Book
import com.caminaapps.bookworm.features.searchBookOnline.domain.SaveBookFromOnlineSearchUseCase
import com.caminaapps.bookworm.features.searchBookOnline.domain.SearchBookByTitleUseCase
import com.caminaapps.bookworm.features.searchBookOnline.presentation.searchTitle.SearchForBookTitleUiState.Empty
import com.caminaapps.bookworm.features.searchBookOnline.presentation.searchTitle.SearchForBookTitleUiState.Loading
import com.caminaapps.bookworm.features.searchBookOnline.presentation.searchTitle.SearchForBookTitleUiState.NoResults
import com.caminaapps.bookworm.features.searchBookOnline.presentation.searchTitle.SearchForBookTitleUiState.Success
import com.caminaapps.bookworm.util.WhileUiSubscribed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class SearchForBookTitleViewModel @Inject constructor(
    private val searchBook: SearchBookByTitleUseCase,
    private val saveBook: SaveBookFromOnlineSearchUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<SearchForBookTitleUiState>(Empty)
    val uiState: StateFlow<SearchForBookTitleUiState> = _uiState.stateIn(
        scope = viewModelScope,
        started = WhileUiSubscribed,
        initialValue = Empty
    )

    fun onQueryChanged() {
        _uiState.value = Empty
    }

    fun search(query: String) {
        if (query.isBlank()) {
            _uiState.update { Empty }
            return
        }

        // TODO() use coroutine job to cancel previous search
        viewModelScope.launch {
            try {
                _uiState.update { Loading }
                val result = searchBook(query)
                when (result.isEmpty()) {
                    true -> _uiState.update { NoResults }
                    false -> _uiState.update { Success(books = result) }
                }
            } catch (e: HttpException) {
                Timber.e(e)
                _uiState.update { SearchForBookTitleUiState.Error(message = R.string.error_general_text) }
            } catch (e: IOException) {
                Timber.e(e)
                _uiState.update { SearchForBookTitleUiState.Error(message = R.string.error_network_issue) }
            }
        }
    }

    fun onAddBook(book: Book) {
        viewModelScope.launch {
            saveBook(book)
        }
    }

//    fun errorMessageShown() {
//        _uiState.update { Empty }
//    }
}

sealed interface SearchForBookTitleUiState {
    object Empty : SearchForBookTitleUiState
    object Loading : SearchForBookTitleUiState
    data class Success(val books: List<Book>) : SearchForBookTitleUiState
    object NoResults : SearchForBookTitleUiState
    data class Error(@StringRes val message: Int) : SearchForBookTitleUiState
}
