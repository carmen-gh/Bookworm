package com.caminaapps.bookworm.core.data.repository

import com.caminaapps.bookworm.core.data.database.BookDao
import com.caminaapps.bookworm.core.data.database.toBook
import com.caminaapps.bookworm.core.data.database.toBookEntity
import com.caminaapps.bookworm.core.model.Book
import com.caminaapps.bookworm.core.model.BookshelfSortOrder
import com.caminaapps.bookworm.core.model.BookshelfSortOrder.AUTHOR_ASC
import com.caminaapps.bookworm.core.model.BookshelfSortOrder.AUTHOR_DESC
import com.caminaapps.bookworm.core.model.BookshelfSortOrder.DATE_ADDED_ASC
import com.caminaapps.bookworm.core.model.BookshelfSortOrder.DATE_ADDED_DESC
import com.caminaapps.bookworm.core.model.BookshelfSortOrder.TITLE_ASC
import com.caminaapps.bookworm.core.model.BookshelfSortOrder.TITLE_DESC
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val bookDao: BookDao
) : BookRepository {

    override fun getAllBooksStream(sortOrder: BookshelfSortOrder): Flow<List<Book>> {
        return when (sortOrder) {
            DATE_ADDED_ASC -> bookDao.getAllBooksStreamSortedByDateAsc()
            DATE_ADDED_DESC -> bookDao.getAllBooksStreamSortedByDateDesc()
            TITLE_ASC -> bookDao.getAllBooksStreamSortedByTitleAsc()
            TITLE_DESC -> bookDao.getAllBooksStreamSortedByTitleDesc()
            AUTHOR_ASC -> bookDao.getAllBooksStreamSortedByAuthorAsc()
            AUTHOR_DESC -> bookDao.getAllBooksStreamSortedByAuthorDesc()
        }.map { listOfBookEntities ->
            listOfBookEntities.map { it.toBook() }
        }
    }

    override fun getBookDetailsStream(id: String): Flow<Book?> =
        bookDao.getBookStream(id).map {
            it?.toBook()
        }

    override suspend fun saveBook(book: Book) {
        val persistableBook = book.toBookEntity()
        bookDao.insertBook(persistableBook)
    }

    override suspend fun deleteBook(id: String) {
        bookDao.deleteById(id)
    }
}
