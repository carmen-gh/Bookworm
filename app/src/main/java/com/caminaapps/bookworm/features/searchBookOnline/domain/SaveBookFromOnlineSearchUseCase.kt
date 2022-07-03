package com.caminaapps.bookworm.features.searchBookOnline.domain

import com.caminaapps.bookworm.core.data.repository.BookRepository
import com.caminaapps.bookworm.core.model.Book
import javax.inject.Inject

class SaveBookFromOnlineSearchUseCase @Inject constructor(
    private val bookRepository: BookRepository,
) {
    suspend operator fun invoke(book: Book) = bookRepository.saveBook(book)
}
