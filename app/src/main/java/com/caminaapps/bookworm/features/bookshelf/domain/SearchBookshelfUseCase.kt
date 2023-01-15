package com.caminaapps.bookworm.features.bookshelf.domain

import com.caminaapps.bookworm.core.data.repository.BookRepository
import com.caminaapps.bookworm.core.model.Book
import com.caminaapps.bookworm.core.model.BookshelfSortOrder.TITLE_ASC
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchBookshelfUseCase @Inject constructor(
    private val bookRepository: BookRepository,
) {
    operator fun invoke(query: String): Flow<List<Book>> =
        bookRepository
            .getAllBooksStream(TITLE_ASC)
//            .filter {  }

}
