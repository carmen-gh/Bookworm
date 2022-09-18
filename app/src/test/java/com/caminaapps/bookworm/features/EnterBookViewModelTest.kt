package com.caminaapps.bookworm.features

import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isEqualTo
import com.caminaapps.bookworm.core.model.Book
import com.caminaapps.bookworm.fake.FakeBookRepository
import com.caminaapps.bookworm.features.enterBook.EnterBookUiState.BookSaved
import com.caminaapps.bookworm.features.enterBook.EnterBookUiState.EnterBookInfo
import com.caminaapps.bookworm.features.enterBook.EnterBookUiState.ErrorTitleMissing
import com.caminaapps.bookworm.features.enterBook.EnterBookViewModel
import com.caminaapps.bookworm.features.enterBook.SaveEnteredBookUseCase
import com.caminaapps.bookworm.util.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class EnterBookViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private var bookRepository = FakeBookRepository()
    private var saveBookUseCase = SaveEnteredBookUseCase(bookRepository)
    private lateinit var viewModel: EnterBookViewModel

    @Before
    fun setup() {
        viewModel = EnterBookViewModel(saveBookUseCase)
    }

    @Test
    fun uiState_whenInitialzed_thenEnterBookInfo() = runTest {
        assertThat(viewModel.uiState.value).isEqualTo(EnterBookInfo)
    }

    @Test
    fun uiState_whenSavedBookSuccessfully_thenBookSaved() = runTest {
        viewModel.saveBook(testInputBook)
        viewModel.uiState.test {
            assertThat(awaitItem()).isEqualTo(BookSaved)
        }
    }

    @Test
    fun uiState_whenSaveWithoutBookTitle_thenErrorTitleMissing() = runTest {
        viewModel.saveBook(testInputBookTitleMissing)
        viewModel.uiState.test {
            assertThat(awaitItem()).isEqualTo(ErrorTitleMissing)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun uiState_whenChangeTitleAfterError_thenEnterBookInfo() = runTest {
        viewModel.uiState.test {
            assertThat(awaitItem()).isEqualTo(EnterBookInfo)
            viewModel.saveBook(testInputBookTitleMissing)
            assertThat(awaitItem()).isEqualTo(ErrorTitleMissing)
            viewModel.titleInputChanged()
            assertThat(awaitItem()).isEqualTo(EnterBookInfo)
            cancelAndIgnoreRemainingEvents()
        }
    }
}

private val testInputBookTitleMissing =
    Book(
        id = "39",
        title = "",
        author = "Carmen",
        publishedDate = "2023",
        subtitle = "",
        coverUrl = null
    )

private val testInputBook =
    Book(
        id = "33343",
        title = "My awesome book",
        author = "Carmen",
        publishedDate = "2023",
        subtitle = "second edition",
        coverUrl = null
    )
