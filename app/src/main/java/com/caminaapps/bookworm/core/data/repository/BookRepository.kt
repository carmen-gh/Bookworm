package com.caminaapps.bookworm.core.data.repository

import com.caminaapps.bookworm.core.model.Book
import com.caminaapps.bookworm.core.model.BookshelfSortOrder
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    fun getAllBooksStream(sortOrder: BookshelfSortOrder): Flow<List<Book>>
    fun getBookDetailsStream(id: String): Flow<Book?>
    suspend fun saveBook(book: Book)
    suspend fun deleteBook(id: String)
}
