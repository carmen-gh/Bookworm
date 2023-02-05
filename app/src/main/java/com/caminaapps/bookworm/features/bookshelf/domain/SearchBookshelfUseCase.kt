package com.caminaapps.bookworm.features.bookshelf.domain

import com.caminaapps.bookworm.core.data.repository.BookRepository
import com.caminaapps.bookworm.core.model.Book
import com.caminaapps.bookworm.core.model.BookshelfSortOrder
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import timber.log.Timber

import javax.inject.Inject

class SearchBookshelfUseCase @Inject constructor(
    private val bookRepository: BookRepository,
    @IoDispatcher private val defaultDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(query: String): List<Book> {
        if (query.isBlank()) {
            return bookRepository.getAllBooksStream(BookshelfSortOrder.TITLE_ASC)
                .first()
        }

        return bookRepository.getAllBooksStream(BookshelfSortOrder.TITLE_ASC)
            .map { books ->
                books.filter { matching(it, query) }
            }
            .onEach {
                Timber.d("Searched Bookshelf for $query, found ${it.count()} results.")
            }
            .flowOn(defaultDispatcher)
            .first()
    }

    private fun matching(book: Book, query: String): Boolean {
        return book.title.contains(query, ignoreCase = true) ||
            book.subtitle.contains(query, ignoreCase = true) ||
            book.author.contains(query, ignoreCase = true)
    }
}
