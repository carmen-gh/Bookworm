package com.caminaapps.bookworm.features.bookshelf

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotEqualTo
import com.caminaapps.bookworm.core.model.Book
import com.caminaapps.bookworm.core.model.BookshelfSortOrder
import com.caminaapps.bookworm.features.bookshelf.domain.GetAllBooksUseCase
import com.caminaapps.bookworm.features.bookshelf.domain.GetBookshelfSortOrderUseCase
import com.caminaapps.bookworm.features.bookshelf.domain.UpdateBookshelfSortOrderUseCase
import com.caminaapps.bookworm.features.bookshelf.presentation.BookshelfUiState
import com.caminaapps.bookworm.features.bookshelf.presentation.BookshelfUiState.Error
import com.caminaapps.bookworm.features.bookshelf.presentation.BookshelfUiState.Loading
import com.caminaapps.bookworm.features.bookshelf.presentation.BookshelfViewModel
import com.caminaapps.bookworm.testing.FakeBookRepository
import com.caminaapps.bookworm.testing.FakeUserPreferencesRepository
import com.caminaapps.bookworm.testing.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
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
        bookRepository.send(bookList.sortedBy { it.author })
        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect() }

        bookRepository.send(bookList.sortedBy { it.author })

        userPreferencesRepository.send(testSortOrderAuthorAsc)

        assertThat((viewModel.uiState.value as BookshelfUiState.Success).books)
            .isEqualTo(bookList)
        assertThat((viewModel.uiState.value as BookshelfUiState.Success).sortOrder)
            .isEqualTo(testSortOrderAuthorAsc)

        collectJob.cancel()
    }

    @Test
    fun uiState_whenSortChange_thenUpdatedSuccessData() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect() }

        bookRepository.send(bookList.sortedBy { it.author })
        userPreferencesRepository.send(testSortOrderAuthorAsc)
        assertThat((viewModel.uiState.value as BookshelfUiState.Success).books)
            .isEqualTo(bookList)
        assertThat((viewModel.uiState.value as BookshelfUiState.Success).sortOrder)
            .isEqualTo(testSortOrderAuthorAsc)

        userPreferencesRepository.send(testSortOrderTitleAsc)

        assertThat((viewModel.uiState.value as BookshelfUiState.Success).books)
            .isNotEqualTo(bookList)
        assertThat((viewModel.uiState.value as BookshelfUiState.Success).sortOrder)
            .isEqualTo(testSortOrderTitleAsc)

        collectJob.cancel()
    }

    @Test
    fun uiState_whenException_thenError() = runTest {
        bookRepository.shouldReturnError = true
        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect() }

        viewModel.updateSortOrder(BookshelfSortOrder.TITLE_ASC)
        assertThat(viewModel.uiState.value).isEqualTo(Error)

        collectJob.cancel()
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
