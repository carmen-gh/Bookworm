package com.caminaapps.bookworm.features.searchBookOnline.domain

import com.caminaapps.bookworm.core.data.repository.OnlineSearchBookRepository
import com.caminaapps.bookworm.core.model.Book
import javax.inject.Inject

class SearchBookByIsbnUseCase @Inject constructor(
    private val onlineSearchBookRepository: OnlineSearchBookRepository,
) {

    suspend operator fun invoke(isbn: String): Book? {
        return if (isbn.isBlank()) {
            null
        } else {
            onlineSearchBookRepository.getBookByISBN(isbn)
        }
    }
}
