package com.caminaapps.bookworm.features.searchBookOnline.domain

import com.caminaapps.bookworm.core.domain.model.Book
import com.caminaapps.bookworm.core.domain.repository.BookRepository
import javax.inject.Inject

class SaveBookFromOnlineSearchUseCase @Inject constructor(
    private val bookRepository: BookRepository,
) {

    suspend operator fun invoke(book: Book) = bookRepository.saveBook(book)

}