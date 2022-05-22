package com.caminaapps.bookworm.features.bookshelf.domain

import com.caminaapps.bookworm.core.domain.model.Book
import com.caminaapps.bookworm.core.domain.repository.BookRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBooksUseCase @Inject constructor(
    private val bookRepository: BookRepository
) {

    operator fun invoke(): Flow<List<Book>> = bookRepository.getAllBooksStream()

}
