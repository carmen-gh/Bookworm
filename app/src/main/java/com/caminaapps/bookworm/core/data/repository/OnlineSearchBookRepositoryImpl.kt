package com.caminaapps.bookworm.core.data.repository

import com.caminaapps.bookworm.core.data.network.OpenLibraryAPI
import com.caminaapps.bookworm.core.data.network.dto.asBook
import com.caminaapps.bookworm.core.model.Book
import javax.inject.Inject

class OnlineSearchBookRepositoryImpl @Inject constructor(
    private val openLibraryAPI: OpenLibraryAPI,
) : OnlineSearchBookRepository {

    override suspend fun getBookByISBN(isbn: String): Book? {
        val searchResult = openLibraryAPI.searchBookByIsbn(isbn)
        return searchResult.docs.firstOrNull()?.asBook()
    }

    override suspend fun getBooksByTitle(title: String): List<Book> {
        val searchResult = openLibraryAPI.searchBookByTitle(title)

        return if (searchResult.docs.isNotEmpty()) {
            searchResult.docs.map { it.asBook() }
        } else {
            emptyList()
        }
    }
}
