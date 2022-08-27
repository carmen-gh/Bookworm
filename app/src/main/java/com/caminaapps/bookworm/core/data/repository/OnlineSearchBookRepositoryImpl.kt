package com.caminaapps.bookworm.core.data.repository

import com.caminaapps.bookworm.core.data.network.OpenLibraryAPI
import com.caminaapps.bookworm.core.data.network.dto.toBook
import com.caminaapps.bookworm.core.model.Book
import javax.inject.Inject

class OnlineSearchBookRepositoryImpl @Inject constructor(
    private val openLibraryAPI: OpenLibraryAPI,
) : OnlineSearchBookRepository {

    override suspend fun getBookByISBN(isbn: String): Book? {
        val searchResult = openLibraryAPI.searchBookByIsbn(isbn)
        return searchResult.docs.firstOrNull()?.toBook()
    }

    override suspend fun getBooksByTitle(title: String): List<Book> {
        val searchResult = openLibraryAPI.searchBookByTitle(title)
        if (searchResult.docs.isEmpty()) return emptyList()
        return searchResult.docs.map { it.toBook() }
    }
}
