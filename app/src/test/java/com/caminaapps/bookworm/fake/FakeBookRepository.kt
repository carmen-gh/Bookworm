package com.caminaapps.bookworm.fake

import com.caminaapps.bookworm.core.data.repository.BookRepository
import com.caminaapps.bookworm.core.model.Book
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

    override fun getAllBooksStreamSortedByDateAsc(): Flow<List<Book>> =
        if (shouldReturnError) {
            flow { throw IllegalStateException("error") }
        } else {
            booksFlow.map { books ->
                books.sortedBy { it.publishedDate }
            }
        }

    override fun getAllBooksStreamSortedByDateDesc(): Flow<List<Book>> =
        if (shouldReturnError) {
            flow { throw IllegalStateException("error") }
        } else {
            booksFlow.map { books ->
                books.sortedByDescending { it.publishedDate }
            }
        }

    override fun getAllBooksStreamSortedByTitleAsc(): Flow<List<Book>> =
        if (shouldReturnError) {
            flow { throw IllegalStateException("error") }
        } else {
            booksFlow.map { books ->
                books.sortedBy { it.title }
            }
        }

    override fun getAllBooksStreamSortedByTitleDesc(): Flow<List<Book>> =
        if (shouldReturnError) {
            flow { throw IllegalStateException("error") }
        } else {
            booksFlow.map { books ->
                books.sortedByDescending { it.title }
            }
        }

    override fun getAllBooksStreamSortedByAuthorAsc(): Flow<List<Book>> =
        if (shouldReturnError) {
            flow { throw IllegalStateException("error") }
        } else {
            booksFlow.map { books ->
                books.sortedBy { it.author }
            }
        }

    override fun getAllBooksStreamSortedByAuthorDesc(): Flow<List<Book>> =
        if (shouldReturnError) {
            flow { throw IllegalStateException("error") }
        } else {
            booksFlow.map { books ->
                books.sortedByDescending { it.author }
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
