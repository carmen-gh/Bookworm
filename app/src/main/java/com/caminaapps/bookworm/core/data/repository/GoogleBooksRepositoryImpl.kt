package com.caminaapps.bookworm.core.data.repository

import com.caminaapps.bookworm.core.data.remote.GoogleBooksApi
import com.caminaapps.bookworm.core.data.remote.dto.toBook
import com.caminaapps.bookworm.core.domain.model.Book
import com.caminaapps.bookworm.core.domain.repository.GoogleBooksRepository
import com.caminaapps.bookworm.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject


class GoogleBooksRepositoryImpl @Inject constructor(
    private val googleBooksApi: GoogleBooksApi,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : GoogleBooksRepository {

    override suspend fun getBookByISBN(isbn: String): Book? = withContext(ioDispatcher) {
        val searchResult = googleBooksApi.getBooks(GoogleBooksApi.parameterISBN(isbn))
        searchResult.items.firstOrNull()?.volumeInfo?.toBook()
    }


    override suspend fun getBooksByTitle(title: String): List<Book> = withContext(ioDispatcher) {
        val searchResult = googleBooksApi.getBooks(GoogleBooksApi.parameterTitle(title))
        searchResult.items.map { it.volumeInfo.toBook() }
    }

}
