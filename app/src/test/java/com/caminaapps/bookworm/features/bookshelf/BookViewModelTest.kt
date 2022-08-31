package com.caminaapps.bookworm.features.bookshelf

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isEqualTo
import com.caminaapps.bookworm.core.model.Book
import com.caminaapps.bookworm.fake.FakeBookRepository
import com.caminaapps.bookworm.features.bookshelf.domain.DeleteBookUseCase
import com.caminaapps.bookworm.features.bookshelf.domain.GetBookDetailsUseCase
import com.caminaapps.bookworm.features.bookshelf.presentation.BookDetailsUiState
import com.caminaapps.bookworm.features.bookshelf.presentation.BookViewModel
import com.caminaapps.bookworm.util.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class BookViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private val bookRepository = FakeBookRepository()
    private val getBookUseCase = GetBookDetailsUseCase(bookRepository)
    private val deleteBookUseCase = DeleteBookUseCase(bookRepository)
    private lateinit var viewModel: BookViewModel

    @Before
    fun setUp() {
        viewModel = BookViewModel(
            savedStateHandle = SavedStateHandle(mapOf("bookId" to testInputBookMatching.id)),
            getBookDetails = getBookUseCase,
            deleteBook = deleteBookUseCase
        )
    }

    @Test
    fun uiState_whenInitialized_thenLoading() = runTest {
        assertThat(viewModel.uiState.value).isEqualTo(BookDetailsUiState.Loading)
    }

    @Test
    fun uiState_whenBookIdNotExist_thenNotFound() = runTest {
        viewModel.uiState.test {
            bookRepository.send(listOf(testInputBookNotMatching))
            assertThat(viewModel.uiState.value).isEqualTo(BookDetailsUiState.NotFound)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun uiState_whenBookIdExist_thenSuccess() = runTest {
        viewModel.uiState.test {
            bookRepository.send(listOf(testInputBookMatching))
            assertThat(viewModel.uiState.value)
                .isEqualTo(BookDetailsUiState.Success(testInputBookMatching))
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun uiState_whenDeleteBook_thenNotFound() = runTest {
        viewModel.uiState.test {
            bookRepository.send(listOf(testInputBookMatching))
            assertThat(viewModel.uiState.value)
                .isEqualTo(BookDetailsUiState.Success(testInputBookMatching))
            viewModel.onDeleteBook(testInputBookMatching.id)
            assertThat(viewModel.uiState.value)
                .isEqualTo(BookDetailsUiState.NotFound)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun uiState_whenException_thenError() = runTest {
        bookRepository.shouldReturnError = true
        viewModel = BookViewModel(
            savedStateHandle = SavedStateHandle(mapOf("bookId" to testInputBookMatching.id)),
            getBookDetails = getBookUseCase,
            deleteBook = deleteBookUseCase
        )
        viewModel.uiState.test {
            assertThat(viewModel.uiState.value).isEqualTo(BookDetailsUiState.Error)
            cancelAndIgnoreRemainingEvents()
        }
        bookRepository.shouldReturnError = false
    }
}


private val testInputBookNotMatching =
    Book(
        id = "different id",
        title = "Testing with Android",
        author = "Carmen",
        publishedDate = "2023",
        subtitle = "",
        coverUrl = null
    )

private val testInputBookMatching =
    Book(
        id = "123",
        title = "Testing with Android",
        author = "Carmen",
        publishedDate = "2023",
        subtitle = "",
        coverUrl = null
    )
