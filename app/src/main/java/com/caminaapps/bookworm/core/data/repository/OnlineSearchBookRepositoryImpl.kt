package com.caminaapps.bookworm.core.data.repository

import com.caminaapps.bookworm.core.data.network.OpenLibraryAPI
import com.caminaapps.bookworm.core.data.network.dto.asBook
import com.caminaapps.bookworm.core.model.Book
import com.caminaapps.bookworm.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class OnlineSearchBookRepositoryImpl @Inject constructor(
    private val openLibraryAPI: OpenLibraryAPI,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : OnlineSearchBookRepository {

    override suspend fun getBookByISBN(isbn: String): Book? = withContext(ioDispatcher) {
        val searchResult = openLibraryAPI.searchBookByIsbn(isbn)
        searchResult.docs.firstOrNull()?.asBook()
    }

    override suspend fun getBooksByTitle(title: String): List<Book> = withContext(ioDispatcher) {
        val searchResult = openLibraryAPI.searchBookByTitle(title)

        return@withContext if (searchResult.docs.isNotEmpty()) {
            searchResult.docs.map { it.asBook() }
        } else {
            emptyList()
        }
    }
}
