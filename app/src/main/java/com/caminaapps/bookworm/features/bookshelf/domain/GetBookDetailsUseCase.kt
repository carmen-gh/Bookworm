package com.caminaapps.bookworm.features.bookshelf.domain

import com.caminaapps.bookworm.core.model.Book
import com.caminaapps.bookworm.core.data.repository.BookRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBookDetailsUseCase @Inject constructor(
    private val bookRepository: BookRepository
) {

    operator fun invoke(id: String): Flow<Book?> = bookRepository.getBookDetailsStream(id)

}
