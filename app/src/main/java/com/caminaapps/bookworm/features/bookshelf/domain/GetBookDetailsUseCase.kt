package com.caminaapps.bookworm.features.bookshelf.domain

import com.caminaapps.bookworm.core.domain.model.Book
import com.caminaapps.bookworm.core.domain.repository.BookRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBookDetailsUseCase @Inject constructor(
    private val bookRepository: BookRepository
) {

    suspend operator fun invoke(id: String): Flow<Book> = bookRepository.getBookDetails(id)

}