package com.caminaapps.bookworm.core.domain.repository

import com.caminaapps.bookworm.core.domain.model.Book
import kotlinx.coroutines.flow.Flow


interface BookRepository {
    fun getAllBooks(): Flow<List<Book>>

    suspend fun saveBook(book: Book)

    // update book
    // suspend fun deleteBook(id: String)
    // suspend fun deleteAllBooks()
}