package com.caminaapps.bookworm.fake

import com.caminaapps.bookworm.core.data.repository.OnlineSearchBookRepository
import com.caminaapps.bookworm.core.model.Book
import java.io.IOException

class FakeOnlineSearchBookRepository : OnlineSearchBookRepository {

    var shouldThrowException = false
    var shouldReturnResult = true

    private var fakeBookResult = Book(
        id = "123",
        title = "Random book",
        subtitle = "",
        author = "Max Mustermann",
        publishedDate = "2009",
        coverUrl = null
    )
    private var fakeBookListResult = listOf(
        Book(
            id = "123",
            title = "Random book",
            subtitle = "",
            author = "Max Mustermann",
            publishedDate = "2009",
            coverUrl = null
        ),
        Book(
            id = "456",
            title = "Random book",
            subtitle = "",
            author = "Mila Mustermann",
            publishedDate = "June 2001",
            coverUrl = null
        )
    )

    override suspend fun getBookByISBN(isbn: String): Book? {
        if (shouldThrowException) {
            throw IOException()
        }
        return if (shouldReturnResult) fakeBookResult else null
    }

    override suspend fun getBooksByTitle(title: String): List<Book> {
        if (shouldThrowException) {
            throw IOException()
        }

        return if (shouldReturnResult) {
            fakeBookListResult.map { it.copy(title = title) }
        } else {
            emptyList()
        }
    }
}
