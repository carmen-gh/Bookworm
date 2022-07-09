package com.caminaapps.bookworm.core.data.repository

import com.caminaapps.bookworm.core.data.database.BookDao
import com.caminaapps.bookworm.core.data.database.toBook
import com.caminaapps.bookworm.core.data.database.toBookEntity
import com.caminaapps.bookworm.core.model.Book
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val bookDao: BookDao
) : BookRepository {

    override fun getAllBooksStream(): Flow<List<Book>> =
        bookDao.getAllBooksStream()
            .map { listOfBookEntities ->
                listOfBookEntities.map { it.toBook() }
            }

    override fun getBookDetailsStream(id: String): Flow<Book?> =
        bookDao.getBookStream(id)
            .map {
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
