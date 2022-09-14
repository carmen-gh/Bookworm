package com.caminaapps.bookworm.features.enterBook

import com.caminaapps.bookworm.core.data.repository.BookRepository
import com.caminaapps.bookworm.core.model.Book
import javax.inject.Inject

class SaveEnteredBookUseCase @Inject constructor(
    private val bookRepository: BookRepository,
) {
    @Throws(BookTitleMissing::class)
    suspend operator fun invoke(
        title: String,
        subtitle: String?,
        author: String?,
        published: String?,
        isFavourite: Boolean,
        finishedReading: Boolean
    ) {
        if (title.isBlank()) throw BookTitleMissing("book title is required field")

        val book = Book(
            title = title,
            subtitle = subtitle ?: "",
            author = author ?: "",
            publishedDate = published ?: "",
            isFavourite = isFavourite,
            finishedReading = finishedReading,
            coverUrl = null
        )
        bookRepository.saveBook(book)
    }
}

class BookTitleMissing(message: String) : Exception(message)
