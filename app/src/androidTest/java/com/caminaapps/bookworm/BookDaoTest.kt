package com.caminaapps.bookworm

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import assertk.assertThat
import assertk.assertions.containsExactly
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import assertk.assertions.isNotZero
import assertk.assertions.isNull
import assertk.assertions.isZero
import com.caminaapps.bookworm.core.data.database.AppDatabase
import com.caminaapps.bookworm.core.data.database.BookDao
import com.caminaapps.bookworm.core.data.database.BookEntity
import com.caminaapps.bookworm.core.data.database.toBook
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.LocalDate
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class BookDaoTest {

    private lateinit var bookDao: BookDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context,
            AppDatabase::class.java
        ).build()
        bookDao = db.bookDao()
    }

    @Test
    fun bookDao_fetches_books_by_date_asc() = runTest {
        val bookEntities = listOf(
            testBookEntity(
                id = "0",
                dateAdded = LocalDate(2020, 1, 1)
            ),
            testBookEntity(
                id = "1",
                dateAdded = LocalDate(2020, 12, 1)
            ),
            testBookEntity(
                id = "2",
                dateAdded = LocalDate(2020, 3, 1)
            ),
            testBookEntity(
                id = "3",
                dateAdded = LocalDate(2020, 2, 1)
            )
        )
        bookEntities.forEach {
            bookDao.insertBook(it)
        }

        val savedBooks = bookDao.getAllBooksStreamSortedByDateAsc().first()
        val datesAdded = savedBooks.map {
            it.toBook().addedToBookshelf
        }
        assertThat(datesAdded)
            .containsExactly(
                LocalDate(2020, 1, 1),
                LocalDate(2020, 2, 1),
                LocalDate(2020, 3, 1),
                LocalDate(2020, 12, 1)
            )
    }

    @Test
    fun bookDao_fetches_books_by_date_desc() = runTest {
        val bookEntities = listOf(
            testBookEntity(
                id = "0",
                dateAdded = LocalDate(2020, 1, 1)
            ),
            testBookEntity(
                id = "1",
                dateAdded = LocalDate(2020, 12, 1)
            ),
            testBookEntity(
                id = "2",
                dateAdded = LocalDate(2020, 3, 1)
            ),
            testBookEntity(
                id = "3",
                dateAdded = LocalDate(2020, 2, 1)
            )
        )
        bookEntities.forEach {
            bookDao.insertBook(it)
        }

        val savedBooks = bookDao.getAllBooksStreamSortedByDateDesc().first()
        val datesAdded = savedBooks.map {
            it.toBook().addedToBookshelf
        }
        assertThat(datesAdded)
            .containsExactly(
                LocalDate(2020, 12, 1),
                LocalDate(2020, 3, 1),
                LocalDate(2020, 2, 1),
                LocalDate(2020, 1, 1)
            )
    }

    @Test
    fun bookDao_fetches_books_by_author_asc() = runTest {
        val bookEntities = listOf(
            testBookEntity(
                id = "0",
                author = "Daniel"
            ),
            testBookEntity(
                id = "1",
                author = "Sabine"
            ),
            testBookEntity(
                id = "2",
                author = "Xavier"
            ),
            testBookEntity(
                id = "3",
                author = "Anja"
            )
        )
        bookEntities.forEach {
            bookDao.insertBook(it)
        }

        val savedBooks = bookDao.getAllBooksStreamSortedByAuthorAsc().first()
        val datesAdded = savedBooks.map {
            it.toBook().author
        }
        assertThat(datesAdded)
            .containsExactly(
                "Anja",
                "Daniel",
                "Sabine",
                "Xavier"
            )
    }

    @Test
    fun bookDao_fetches_books_by_author_desc() = runTest {
        val bookEntities = listOf(
            testBookEntity(
                id = "0",
                author = "Daniel"
            ),
            testBookEntity(
                id = "1",
                author = "Sabine"
            ),
            testBookEntity(
                id = "2",
                author = "Xavier"
            ),
            testBookEntity(
                id = "3",
                author = "Anja"
            )
        )
        bookEntities.forEach {
            bookDao.insertBook(it)
        }

        val savedBooks = bookDao.getAllBooksStreamSortedByAuthorDesc().first()
        val datesAdded = savedBooks.map {
            it.toBook().author
        }
        assertThat(datesAdded)
            .containsExactly(
                "Xavier",
                "Sabine",
                "Daniel",
                "Anja"
            )
    }

    @Test
    fun bookDao_fetches_books_by_title_asc() = runTest {
        val bookEntities = listOf(
            testBookEntity(
                id = "0",
                title = "Destination"
            ),
            testBookEntity(
                id = "1",
                title = "Stars"
            ),
            testBookEntity(
                id = "2",
                title = "Zebras"
            ),
            testBookEntity(
                id = "3",
                title = "Android"
            )
        )
        bookEntities.forEach {
            bookDao.insertBook(it)
        }

        val savedBooks = bookDao.getAllBooksStreamSortedByTitleAsc().first()
        val datesAdded = savedBooks.map {
            it.toBook().title
        }
        assertThat(datesAdded)
            .containsExactly(
                "Android",
                "Destination",
                "Stars",
                "Zebras"
            )
    }

    @Test
    fun bookDao_fetches_books_by_title_desc() = runTest {
        val bookEntities = listOf(
            testBookEntity(
                id = "0",
                title = "Destination"
            ),
            testBookEntity(
                id = "1",
                title = "Stars"
            ),
            testBookEntity(
                id = "2",
                title = "Zebras"
            ),
            testBookEntity(
                id = "3",
                title = "Android"
            )
        )
        bookEntities.forEach {
            bookDao.insertBook(it)
        }

        val savedBooks = bookDao.getAllBooksStreamSortedByTitleDesc().first()
        val datesAdded = savedBooks.map {
            it.toBook().title
        }
        assertThat(datesAdded)
            .containsExactly(
                "Zebras",
                "Stars",
                "Destination",
                "Android"
            )
    }

    @Test
    fun bookDao_fetches_bookById_isNotNull() = runTest {
        val bookEntities = listOf(
            testBookEntity(
                id = "0",
                title = "Destination"
            ),
            testBookEntity(
                id = "1",
                title = "Stars"
            ),
            testBookEntity(
                id = "2",
                title = "Zebras"
            ),
            testBookEntity(
                id = "3",
                title = "Android"
            )
        )
        bookEntities.forEach {
            bookDao.insertBook(it)
        }

        val savedBook = bookDao.getBookStream("2").first()
        assertThat(savedBook)
            .isNotNull()
            .isEqualTo(bookEntities[2])
    }

    @Test
    fun bookDao_fetches_bookById_isNull() = runTest {
        val bookEntities = listOf(
            testBookEntity(
                id = "0",
                title = "Destination"
            ),
            testBookEntity(
                id = "1",
                title = "Stars"
            ),
            testBookEntity(
                id = "2",
                title = "Zebras"
            ),
            testBookEntity(
                id = "3",
                title = "Android"
            )
        )
        bookEntities.forEach {
            bookDao.insertBook(it)
        }

        val savedBook = bookDao.getBookStream("A").first()
        assertThat(savedBook).isNull()
    }

    @Test
    fun bookDao_update_book() = runTest {
        val bookEntity = testBookEntity(
            id = "2",
            title = "Zebras"
        )
        bookDao.insertBook(bookEntity)
        bookDao.updateBook(bookEntity.copy(title = "All Zebras"))

        val updatedBook = bookDao.getBookStream(bookEntity.id).first()

        assertThat(updatedBook).isNotNull()
        assertThat(updatedBook?.title).isEqualTo("All Zebras")
    }

    @Test
    fun bookDao_deleteById_removes_book() = runTest {
        val bookEntity = testBookEntity(
            id = "2",
            title = "Zebras"
        )
        bookDao.insertBook(bookEntity)
        assertThat(bookDao.getAllBooksStreamSortedByTitleAsc().first().count()).isNotZero()

        bookDao.deleteById("2")

        assertThat(bookDao.getAllBooksStreamSortedByTitleAsc().first().count()).isZero()
    }

    @Test
    fun bookDao_delete_removes_book() = runTest {
        val bookEntity = testBookEntity(
            id = "2",
            title = "Zebras"
        )
        bookDao.insertBook(bookEntity)
        assertThat(bookDao.getAllBooksStreamSortedByTitleAsc().first().count()).isNotZero()

        bookDao.delete(bookEntity)

        assertThat(bookDao.getAllBooksStreamSortedByTitleAsc().first().count()).isZero()
    }

    @Test
    fun bookDao_deleteAll_removes_book() = runTest {
        val bookEntities = listOf(
            testBookEntity(
                id = "0",
                title = "Destination"
            ),
            testBookEntity(
                id = "1",
                title = "Stars"
            ),
            testBookEntity(
                id = "2",
                title = "Zebras"
            ),
            testBookEntity(
                id = "3",
                title = "Android"
            )
        )
        bookEntities.forEach {
            bookDao.insertBook(it)
        }
        assertThat(bookDao.getAllBooksStreamSortedByTitleAsc().first().count()).isNotZero()

        bookDao.deleteAll()

        assertThat(bookDao.getAllBooksStreamSortedByTitleAsc().first().count()).isZero()
    }
}

private fun testBookEntity(
    id: String = "0",
    title: String = "",
    dateAdded: LocalDate = LocalDate(2023, 1, 3),
    author: String = ""
) = BookEntity(
    id = id,
    title = title,
    subtitle = "",
    author = author,
    publishedDate = "",
    coverUrl = null,
    finishedReading = false,
    isFavourite = false,
    addedToBookshelf = dateAdded
)
