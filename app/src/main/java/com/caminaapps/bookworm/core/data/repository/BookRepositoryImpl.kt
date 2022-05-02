package com.caminaapps.bookworm.core.data.repository

import com.caminaapps.bookworm.core.data.local.BookDao
import com.caminaapps.bookworm.core.data.local.BookEntity
import com.caminaapps.bookworm.core.domain.model.Book
import com.caminaapps.bookworm.core.domain.repository.BookRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val bookDao: BookDao
) : BookRepository {

    override fun getAllBooks(): Flow<List<Book>> =
        bookDao.getAllBooks()
            .map { listOfBookEntities ->
                listOfBookEntities.map { it.toBook() }
            }

    override fun getBookDetails(id: String): Flow<Book?> =
        bookDao.getBook(id)
            .map {
                it?.toBook()
            }

    override suspend fun saveBook(book: Book) {
        val persistableBook = BookEntity.fromBook(book)
        bookDao.insertBook(persistableBook)
    }

    override suspend fun deleteBook(id: String) {
        bookDao.deleteById(id)
    }
}