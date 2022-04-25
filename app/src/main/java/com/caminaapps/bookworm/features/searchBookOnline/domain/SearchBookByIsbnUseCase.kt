package com.caminaapps.bookworm.features.searchBookOnline.domain

import com.caminaapps.bookworm.core.domain.model.Book
import com.caminaapps.bookworm.core.domain.repository.GoogleBooksRepository
import javax.inject.Inject

class SearchBookByIsbnUseCase @Inject constructor(
    private val googleBooksRepository: GoogleBooksRepository,
) {

    suspend operator fun invoke(isbn: String): Book? {
        return if (isbn.isNotBlank()) {
            googleBooksRepository.getBookByISBN(isbn)
        } else {
            null
        }
    }

}