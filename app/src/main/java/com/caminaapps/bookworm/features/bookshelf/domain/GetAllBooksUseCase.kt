package com.caminaapps.bookworm.features.bookshelf.domain

import com.caminaapps.bookworm.core.data.repository.BookRepository
import com.caminaapps.bookworm.core.data.repository.UserPreferencesRepository
import com.caminaapps.bookworm.core.model.Book
import com.caminaapps.bookworm.core.model.BookshelfSortOrder.AUTHOR_ASC
import com.caminaapps.bookworm.core.model.BookshelfSortOrder.AUTHOR_DESC
import com.caminaapps.bookworm.core.model.BookshelfSortOrder.DATE_ADDED_ASC
import com.caminaapps.bookworm.core.model.BookshelfSortOrder.DATE_ADDED_DESC
import com.caminaapps.bookworm.core.model.BookshelfSortOrder.TITLE_ASC
import com.caminaapps.bookworm.core.model.BookshelfSortOrder.TITLE_DESC
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

class GetAllBooksUseCase @Inject constructor(
    private val bookRepository: BookRepository,
    private val userPreferencesRepository: UserPreferencesRepository
) {

    @OptIn(ExperimentalCoroutinesApi::class)
    operator fun invoke(): Flow<List<Book>> {
        return userPreferencesRepository.bookshelfSortOrder.flatMapLatest {
            when (it) {
                DATE_ADDED_ASC -> bookRepository.getAllBooksStreamSortedByDateAsc()
                DATE_ADDED_DESC -> bookRepository.getAllBooksStreamSortedByDateDesc()
                TITLE_ASC -> bookRepository.getAllBooksStreamSortedByTitleAsc()
                TITLE_DESC -> bookRepository.getAllBooksStreamSortedByTitleDesc()
                AUTHOR_ASC -> bookRepository.getAllBooksStreamSortedByAuthorAsc()
                AUTHOR_DESC -> bookRepository.getAllBooksStreamSortedByAuthorDesc()
            }
        }
    }

}
