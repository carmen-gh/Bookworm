package com.caminaapps.bookworm.features.bookshelf.domain

import com.caminaapps.bookworm.core.data.repository.BookRepository
import javax.inject.Inject

class GetBookDetailsUseCase @Inject constructor(
    private val bookRepository: BookRepository
) {
    operator fun invoke(id: String) = bookRepository.getBookDetailsStream(id)
}
