package com.caminaapps.bookworm.features.searchBookOnline.domain

import com.caminaapps.bookworm.core.domain.model.Book
import com.caminaapps.bookworm.core.domain.repository.GoogleBooksRepository
import javax.inject.Inject

class SearchBookByTitleUseCase @Inject constructor(
    private val googleBooksRepository: GoogleBooksRepository,
) {
    suspend operator fun invoke(title: String): List<Book> {
        if (title.isBlank()) {
            return emptyList()
        }
        return googleBooksRepository.getBooksByTitle(title)
    }
}