package com.caminaapps.bookworm.features.bookshelf.domain

import com.caminaapps.bookworm.core.data.repository.BookRepository
import javax.inject.Inject

class DeleteBookUseCase @Inject constructor(
    private val bookRepository: BookRepository
) {

    suspend operator fun invoke(id: String): Unit = bookRepository.deleteBook(id)

}
