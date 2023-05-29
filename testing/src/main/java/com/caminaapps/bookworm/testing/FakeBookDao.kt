package com.caminaapps.bookworm.testing

import com.caminaapps.bookworm.core.data.database.BookDao
import com.caminaapps.bookworm.core.data.database.BookEntity
import com.caminaapps.bookworm.util.replaceFirst
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

class FakeBookDao : BookDao {

    private var entitiesStateFlow = MutableStateFlow(
        emptyList<BookEntity>(),
    )

    override fun getAllBooksStreamSortedByDateAsc(): Flow<List<BookEntity>> =
        entitiesStateFlow.map { list ->
            list.sortedBy(BookEntity::addedToBookshelf)
        }

    override fun getAllBooksStreamSortedByDateDesc(): Flow<List<BookEntity>> =
        entitiesStateFlow.map { list ->
            list.sortedByDescending(BookEntity::addedToBookshelf)
        }

    override fun getAllBooksStreamSortedByTitleAsc(): Flow<List<BookEntity>> =
        entitiesStateFlow.map { list ->
            list.sortedWith(compareBy(String.CASE_INSENSITIVE_ORDER, BookEntity::title))
        }

    override fun getAllBooksStreamSortedByTitleDesc(): Flow<List<BookEntity>> =
        entitiesStateFlow.map { list ->
            list.sortedWith(compareByDescending(String.CASE_INSENSITIVE_ORDER, BookEntity::title))
        }


    override fun getAllBooksStreamSortedByAuthorAsc(): Flow<List<BookEntity>> =
        entitiesStateFlow.map { list ->
            list.sortedWith(compareBy(String.CASE_INSENSITIVE_ORDER, BookEntity::author))
        }

    override fun getAllBooksStreamSortedByAuthorDesc(): Flow<List<BookEntity>> =
        entitiesStateFlow.map { list ->
            list.sortedWith(compareByDescending(String.CASE_INSENSITIVE_ORDER, BookEntity::author))
        }

    override fun getBookStream(bookId: String): Flow<BookEntity?> =
        entitiesStateFlow.map { list ->
            list.firstOrNull { it.id == bookId }
        }

    override suspend fun insertBook(book: BookEntity) =
        entitiesStateFlow.update {
            (it + book).distinctBy(BookEntity::id)
        }

    suspend fun insertBooks(books: List<BookEntity>) =
        entitiesStateFlow.update { list ->
            (list + books).distinctBy(BookEntity::id)
        }

    override suspend fun updateBook(book: BookEntity) =
        entitiesStateFlow.update { list ->
            list.replaceFirst(book) {
                it.id == book.id
            }
        }

    override suspend fun deleteById(bookId: String) {
        entitiesStateFlow.update { list ->
            list.filterNot { it.id == bookId }
        }
    }

    override suspend fun delete(book: BookEntity) {
        entitiesStateFlow.update { list ->
            list.minus(book)
        }
    }

    override suspend fun deleteAll() {
        entitiesStateFlow.update {
            emptyList()
        }
    }
}
