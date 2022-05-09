package com.caminaapps.bookworm.features.searchBookOnline.domain

import com.caminaapps.bookworm.core.domain.model.Book
import com.caminaapps.bookworm.core.domain.repository.OnlineSearchBookRepository
import javax.inject.Inject

class SearchBookByTitleUseCase @Inject constructor(
    private val onlineSearchBookRepository: OnlineSearchBookRepository,
) {
    suspend operator fun invoke(title: String): List<Book> {
        if (title.isBlank()) {
            return emptyList()
        }
        return onlineSearchBookRepository.getBooksByTitle(title)
    }
}