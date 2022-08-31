package com.caminaapps.bookworm.fake

import com.caminaapps.bookworm.core.data.repository.OnlineSearchBookRepository
import com.caminaapps.bookworm.core.model.Book

class FakeOnlineSearchBookRepository : OnlineSearchBookRepository {

    private var shouldReturnResult = true
    private var searchResultByIsbn = Book(
        id = "123",
        title = "Random book",
        subtitle = "",
        author = "Max Mustermann",
        publishedDate = "2009",
        coverUrl = null
    )
    private var searchResultByTitle = listOf(
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


    override suspend fun getBookByISBN(isbn: String): Book? =
        if (shouldReturnResult) searchResultByIsbn else null


    override suspend fun getBooksByTitle(title: String): List<Book> =
        if (shouldReturnResult) {
            searchResultByTitle.map { it.copy(title = title) }
        } else {
            emptyList()
        }

}
