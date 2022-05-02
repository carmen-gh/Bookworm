package com.caminaapps.bookworm.core.domain.repository

import com.caminaapps.bookworm.core.domain.model.Book
import kotlinx.coroutines.flow.Flow


interface BookRepository {

    fun getAllBooks(): Flow<List<Book>>
    fun getBookDetails(id: String): Flow<Book>

    suspend fun saveBook(book: Book)
    suspend fun deleteBook(id: String)

}