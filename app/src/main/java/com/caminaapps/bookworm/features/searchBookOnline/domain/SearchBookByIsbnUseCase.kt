package com.caminaapps.bookworm.features.searchBookOnline.domain

import com.caminaapps.bookworm.core.model.Book
import com.caminaapps.bookworm.core.data.repository.OnlineSearchBookRepository
import javax.inject.Inject

class SearchBookByIsbnUseCase @Inject constructor(
    private val onlineSearchBookRepository: OnlineSearchBookRepository,
) {

    suspend operator fun invoke(isbn: String): Book? {
        return if (isbn.isNotBlank()) {
            onlineSearchBookRepository.getBookByISBN(isbn)
        } else {
            null
        }
    }

}
