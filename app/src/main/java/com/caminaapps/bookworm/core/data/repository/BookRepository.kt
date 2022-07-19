package com.caminaapps.bookworm.core.data.repository

import com.caminaapps.bookworm.core.model.Book
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    fun getAllBooksStreamSortedByDateAsc(): Flow<List<Book>>
    fun getAllBooksStreamSortedByDateDesc(): Flow<List<Book>>
    fun getAllBooksStreamSortedByTitleAsc(): Flow<List<Book>>
    fun getAllBooksStreamSortedByTitleDesc(): Flow<List<Book>>
    fun getAllBooksStreamSortedByAuthorAsc(): Flow<List<Book>>
    fun getAllBooksStreamSortedByAuthorDesc(): Flow<List<Book>>
    fun getBookDetailsStream(id: String): Flow<Book?>
    suspend fun saveBook(book: Book)
    suspend fun deleteBook(id: String)
}
