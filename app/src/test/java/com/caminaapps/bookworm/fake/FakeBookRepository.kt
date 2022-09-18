package com.caminaapps.bookworm.fake

import com.caminaapps.bookworm.core.data.repository.BookRepository
import com.caminaapps.bookworm.core.model.Book
import com.caminaapps.bookworm.core.model.BookshelfSortOrder
import com.caminaapps.bookworm.core.model.BookshelfSortOrder.AUTHOR_ASC
import com.caminaapps.bookworm.core.model.BookshelfSortOrder.AUTHOR_DESC
import com.caminaapps.bookworm.core.model.BookshelfSortOrder.DATE_ADDED_ASC
import com.caminaapps.bookworm.core.model.BookshelfSortOrder.DATE_ADDED_DESC
import com.caminaapps.bookworm.core.model.BookshelfSortOrder.TITLE_ASC
import com.caminaapps.bookworm.core.model.BookshelfSortOrder.TITLE_DESC
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class FakeBookRepository : BookRepository {

    private var booksFlow =
        MutableSharedFlow<List<Book>>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    private val currentBooks: List<Book>
        get() = booksFlow.replayCache.firstOrNull() ?: emptyList()

    var shouldReturnError: Boolean = false

    fun send(books: List<Book>) {
        booksFlow.tryEmit(books)
    }

    override fun getAllBooksStream(sortOrder: BookshelfSortOrder): Flow<List<Book>> {
        return if (shouldReturnError) {
            flow { throw IllegalStateException("error") }
        } else {
            sortedBooks(sortOrder)
        }
    }

    private fun sortedBooks(order: BookshelfSortOrder): Flow<List<Book>> {
        return booksFlow.map { books ->
            when (order) {
                DATE_ADDED_ASC -> books.sortedBy { it.publishedDate }
                DATE_ADDED_DESC -> books.sortedByDescending { it.publishedDate }
                TITLE_ASC -> books.sortedBy { it.title }
                TITLE_DESC -> books.sortedByDescending { it.title }
                AUTHOR_ASC -> books.sortedBy { it.author }
                AUTHOR_DESC -> books.sortedByDescending { it.author }
            }
        }
    }

    override fun getBookDetailsStream(id: String): Flow<Book?> =
        if (shouldReturnError) {
            flow { throw IllegalStateException("error") }
        } else {
            booksFlow.map { books ->
                books.find { it.id == id }
            }
        }

    override suspend fun saveBook(book: Book) {
        val newList = currentBooks.toMutableList().apply {
            add(book)
        }
        booksFlow.emit(newList)
    }

    override suspend fun deleteBook(id: String) {
        val newList = currentBooks.toMutableList().apply {
            val book = find { it.id == id }
            remove(book)
        }
        booksFlow.emit(newList)
    }

}
