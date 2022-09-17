package com.caminaapps.bookworm.features.enterBook

import com.caminaapps.bookworm.core.data.repository.BookRepository
import com.caminaapps.bookworm.core.model.Book
import javax.inject.Inject

class SaveEnteredBookUseCase @Inject constructor(
    private val bookRepository: BookRepository,
) {
    @Throws(BookTitleMissing::class)
    suspend operator fun invoke(book: Book) {
        if (book.title.isBlank()) throw BookTitleMissing("book title is required field")
        bookRepository.saveBook(book)
    }
}

class BookTitleMissing(message: String) : Exception(message)
