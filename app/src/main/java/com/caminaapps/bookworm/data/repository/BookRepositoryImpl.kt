package com.caminaapps.bookworm.data.repository

import com.caminaapps.bookworm.data.local.BookDao
import com.caminaapps.bookworm.data.local.BookEntity
import com.caminaapps.bookworm.domain.model.Book
import com.caminaapps.bookworm.domain.repository.BookRepository
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

    override suspend fun saveBook(book: Book) {
        val persistableBook = BookEntity.fromBook(book)
        bookDao.insertBook(persistableBook)
    }
}