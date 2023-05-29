package com.caminaapps.bookworm.data

import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import com.caminaapps.bookworm.core.data.database.toBook
import com.caminaapps.bookworm.core.data.repository.DefaultBookRepository
import com.caminaapps.bookworm.core.model.BookshelfSortOrder.DATE_ADDED_ASC
import com.caminaapps.bookworm.core.model.BookshelfSortOrder.DATE_ADDED_DESC
import com.caminaapps.bookworm.core.model.BookshelfSortOrder.TITLE_ASC
import com.caminaapps.bookworm.testing.FakeBookDao
import com.caminaapps.bookworm.testing.testBookEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.LocalDate
import org.junit.After
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class BookRepositoryTest {

    private val bookDao = FakeBookDao()
    private val bookRepo = DefaultBookRepository(bookDao)

    @After
    fun tearDown() {
        runBlocking {
            bookDao.deleteAll()
        }
    }

    @Test
    fun getBookUpdates_when_book_got_changed() = runTest {
        val bookEntity = testBookEntity("Old title")
        val updatedTitle = "New title"
        bookDao.insertBook(bookEntity)
        bookRepo.getBookDetailsStream(bookEntity.id).test {
            assertThat(awaitItem()!!.title).isEqualTo(bookEntity.title)
            bookDao.updateBook(bookEntity.copy(title = updatedTitle))
            assertThat(awaitItem()!!.title).isEqualTo(updatedTitle)
        }
    }

    @Test
    fun bookStream_book_get_deleted_then_get_null() = runTest {
        val bookEntity = testBookEntity()
        bookDao.insertBook(bookEntity)
        bookRepo.getBookDetailsStream(bookEntity.id).test {
            assertThat(awaitItem()).isNotNull()
            bookDao.deleteById(bookEntity.id)
            assertThat(awaitItem()).isNull()
        }
    }

    @Test
    fun bookStream_book_get_added_then_get_not_null() = runTest {
        val bookEntity = testBookEntity()
        bookRepo.getBookDetailsStream(bookEntity.id).test {
            assertThat(awaitItem()).isNull()
            bookDao.insertBook(bookEntity)
            assertThat(awaitItem()?.id).isNotNull().isEqualTo(bookEntity.id)
        }
    }

    @Test
    fun getAllBooksStream_sortOrder_is_DateAdded_asc() = runTest {
        val book0 = testBookEntity(
            id = "0",
            dateAdded = LocalDate(2020, 4, 1)
        )
        val book1 = testBookEntity(
            id = "1",
            dateAdded = LocalDate(2020, 2, 12)
        )
        val book2 = testBookEntity(
            id = "2",
            dateAdded = LocalDate(2020, 3, 6)
        )
        val expectedList = listOf(book1, book2, book0).map { it.toBook() }

        bookRepo.getAllBooksStream(DATE_ADDED_ASC).test {
            awaitItem()
            bookDao.insertBooks(listOf(book0, book1, book2))
            assertThat(awaitItem()).isEqualTo(expectedList)
        }
    }

    @Test
    fun getAllBooksStream_sortOrder_is_DateAdded_desc() = runTest {
        val book0 = testBookEntity(
            id = "0",
            dateAdded = LocalDate(2020, 4, 1)
        )
        val book1 = testBookEntity(
            id = "1",
            dateAdded = LocalDate(2020, 2, 12)
        )
        val book2 = testBookEntity(
            id = "2",
            dateAdded = LocalDate(2020, 3, 6)
        )
        val expectedList = listOf(book0, book2, book1).map { it.toBook() }

        bookRepo.getAllBooksStream(DATE_ADDED_DESC).test {
            awaitItem()
            bookDao.insertBooks(listOf(book0, book1, book2))
            assertThat(awaitItem()).isEqualTo(expectedList)
        }
    }

    @Test
    fun getAllBooksStream_sortOrder_is_title_asc() = runTest {
        val book0 = testBookEntity(
            id = "0",
            title = "because"
        )
        val book1 = testBookEntity(
            id = "1",
            title = "An unexpected book"
        )
        val book2 = testBookEntity(
            id = "2",
            title = "Zebra"
        )
        val expectedList = listOf(book1, book0, book2).map { it.toBook() }

        bookRepo.getAllBooksStream(TITLE_ASC).test {
            awaitItem()
            bookDao.insertBooks(listOf(book0, book1, book2))
            assertThat(awaitItem()).isEqualTo(expectedList)
        }
    }
}
