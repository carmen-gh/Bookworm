package com.caminaapps.bookworm.features.bookshelf

import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import com.caminaapps.bookworm.core.model.Book
import com.caminaapps.bookworm.core.model.BookshelfSortOrder
import com.caminaapps.bookworm.features.bookshelf.domain.GetAllBooksUseCase
import com.caminaapps.bookworm.features.bookshelf.domain.GetBookshelfSortOrderUseCase
import com.caminaapps.bookworm.features.bookshelf.domain.UpdateBookshelfSortOrderUseCase
import com.caminaapps.bookworm.features.bookshelf.presentation.BookshelfUiState
import com.caminaapps.bookworm.features.bookshelf.presentation.BookshelfUiState.Loading
import com.caminaapps.bookworm.features.bookshelf.presentation.BookshelfUiState.Success
import com.caminaapps.bookworm.features.bookshelf.presentation.BookshelfViewModel
import com.caminaapps.bookworm.testing.FakeBookRepository
import com.caminaapps.bookworm.testing.FakeUserPreferencesRepository
import com.caminaapps.bookworm.testing.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class BookshelfViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private lateinit var bookRepository: FakeBookRepository
    private lateinit var userPreferencesRepository: FakeUserPreferencesRepository
    private lateinit var getAllBooksUseCase: GetAllBooksUseCase
    private lateinit var getBookshelfSortOrder: GetBookshelfSortOrderUseCase
    private lateinit var updateBookshelfSortOrder: UpdateBookshelfSortOrderUseCase
    private lateinit var viewModel: BookshelfViewModel

    @Before
    fun setUp() {
        bookRepository = FakeBookRepository()
        userPreferencesRepository = FakeUserPreferencesRepository()
        getAllBooksUseCase = GetAllBooksUseCase(bookRepository, userPreferencesRepository)
        getBookshelfSortOrder = GetBookshelfSortOrderUseCase(userPreferencesRepository)
        updateBookshelfSortOrder = UpdateBookshelfSortOrderUseCase(userPreferencesRepository)

        viewModel = BookshelfViewModel(
            getAllBooksUseCase,
            getBookshelfSortOrder,
            updateBookshelfSortOrder
        )
    }

    @After
    fun tearDown() {
        bookRepository.shouldReturnError = false
        userPreferencesRepository.returnError = false
    }

    @Test
    fun uiState_whenInitialized_thenLoading() = runTest {
        assertThat(viewModel.uiState.value).isEqualTo(Loading)
    }

    @Test
    fun uiState_whenBooksLoaded_thenSuccess() = runTest {

        viewModel.uiState.test {
            awaitItem() // initial state

            bookRepository.send(bookList.sortedBy { it.author })
            userPreferencesRepository.send(testSortOrderAuthorAsc)

            val uiState = awaitItem() as Success
            assertThat(uiState.books).isEqualTo(bookList)
            assertThat(uiState.sortOrder).isEqualTo(testSortOrderAuthorAsc)
        }
    }

    @Test
    fun uiState_whenSortChange_thenUpdatedSuccessData() = runTest {
        viewModel.uiState.test {
            awaitItem() // initial state

            bookRepository.send(bookList.sortedBy { it.author })
            userPreferencesRepository.send(testSortOrderAuthorAsc)

            val uiState1 = awaitItem() as Success
            assertThat(uiState1.sortOrder).isEqualTo(testSortOrderAuthorAsc)

            userPreferencesRepository.send(testSortOrderTitleAsc)
            skipItems(1)
            val uiState = awaitItem() as Success
            assertThat(uiState.sortOrder).isEqualTo(testSortOrderTitleAsc)
            assertThat(uiState.books).isEqualTo(bookList.sortedBy { it.title })
        }
    }

    @Test
    fun uiState_whenException_thenError() = runTest {
        bookRepository.shouldReturnError = true
        viewModel.uiState.test {
            awaitItem() // initial state
            viewModel.updateSortOrder(BookshelfSortOrder.TITLE_ASC)
            assertThat(awaitItem()).isInstanceOf(BookshelfUiState.Error::class)
        }
    }
}

private val testBook1 =
    Book(
        id = "different id",
        title = "Testing with Android",
        author = "Carmen",
        publishedDate = "2023",
        subtitle = "",
        coverUrl = null
    )

private val testBook2 =
    Book(
        id = "123",
        title = "Android and more",
        author = "Zoro",
        publishedDate = "2023",
        subtitle = "",
        coverUrl = null
    )

private val bookList = listOf(testBook1, testBook2)
private val testSortOrderAuthorAsc = BookshelfSortOrder.AUTHOR_ASC
private val testSortOrderTitleAsc = BookshelfSortOrder.TITLE_ASC
