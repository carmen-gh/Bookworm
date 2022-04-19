package com.caminaapps.bookworm.searchBookOnline.domain

import com.caminaapps.bookworm.domain.model.Book
import com.caminaapps.bookworm.domain.repository.GoogleBooksRepository
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