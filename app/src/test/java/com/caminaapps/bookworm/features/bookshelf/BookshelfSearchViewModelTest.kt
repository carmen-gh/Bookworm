package com.caminaapps.bookworm.features.bookshelf

import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.hasSameSizeAs
import assertk.assertions.hasSize
import assertk.assertions.isEmpty
import com.caminaapps.bookworm.core.model.Book
import com.caminaapps.bookworm.features.bookshelf.domain.SearchBookshelfUseCase
import com.caminaapps.bookworm.features.bookshelf.presentation.BookshelfSearchViewModel
import com.caminaapps.bookworm.testing.FakeBookRepository
import com.caminaapps.bookworm.testing.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class BookshelfSearchViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var bookRepository: FakeBookRepository
    private lateinit var searchUseCase: SearchBookshelfUseCase
    private lateinit var viewModel: BookshelfSearchViewModel

    @Before
    fun setUp() {
        bookRepository = FakeBookRepository()
        searchUseCase = SearchBookshelfUseCase(bookRepository, mainDispatcherRule.testDispatcher)
        viewModel = BookshelfSearchViewModel(searchUseCase)
    }

    @Test
    fun searchResults_whenInitialized_thenAllBooks() = runTest {
        bookRepository.send(testBooks)
        viewModel.searchResults.test {
            assertThat(awaitItem()).isEmpty()
            assertThat(awaitItem()).hasSameSizeAs(testBooks)
        }
    }

    @Test
    fun searchResults_whenEmptyQuery_thenAllBooks() = runTest {
        bookRepository.send(testBooks)
        viewModel.searchResults.test {
            awaitItem() // initial is empty
            val allBooks = awaitItem()
            assertThat(allBooks).hasSameSizeAs(testBooks)
            viewModel.onSearch("hall") // first change search query cause of distinctUntilChange
            awaitItem()

            viewModel.onSearch("")
            val result = awaitItem()
            assertThat(result).hasSameSizeAs(testBooks)
        }
    }

    @Test
    fun searResults_whenQueryIsSame_thenNoNewResultsReceived() = runTest {
        bookRepository.send(testBooks)
        viewModel.searchResults.test {
            awaitItem() // initial is empty
            val allBooks = awaitItem()
            assertThat(allBooks).hasSize(testBooks.size)

            viewModel.onSearch("") // initial query is also ""
            expectNoEvents()
        }
    }

    @Test
    fun searchResults_whenQuery_thenFilteredBooks() = runTest {
        bookRepository.send(testBooks)
        viewModel.searchResults.test {
            awaitItem() // initial is empty
            val allBooks = awaitItem()
            assertThat(allBooks).hasSize(testBooks.size)

            viewModel.onSearch("android and more")
            val result = awaitItem()
            assertThat(result).hasSize(1)
        }
    }
}

val testBooks = listOf(
    Book(
        id = "123",
        title = "Android and more",
        author = "Zoro",
        publishedDate = "2023",
        subtitle = "",
        coverUrl = null
    ),
    Book(
        id = "3848458",
        title = "Testing",
        author = "Wonderwoman",
        publishedDate = "2023",
        subtitle = "with Android",
        coverUrl = null
    ),
    Book(
        id = "3848458",
        title = "Flutter Development",
        author = "Jose Diego",
        publishedDate = "2023",
        subtitle = "for real",
        coverUrl = null
    )
)
