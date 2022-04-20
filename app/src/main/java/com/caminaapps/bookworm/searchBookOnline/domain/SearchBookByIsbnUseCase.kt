package com.caminaapps.bookworm.searchBookOnline.domain

import com.caminaapps.bookworm.domain.model.Book
import com.caminaapps.bookworm.domain.repository.GoogleBooksRepository
import javax.inject.Inject

class SearchBookByIsbnUseCase @Inject constructor(
    private val googleBooksRepository: GoogleBooksRepository,
) {
    
    suspend operator fun invoke(isbn: String): Book? {
        if (isbn.isBlank()) {
            return null
        }
        return googleBooksRepository.getBookByISBN(isbn)
    }

}