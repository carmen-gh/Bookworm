package com.caminaapps.bookworm.data.repository

import com.caminaapps.bookworm.data.remote.GoogleBooksApi
import com.caminaapps.bookworm.data.remote.toBook
import com.caminaapps.bookworm.di.IoDispatcher
import com.caminaapps.bookworm.domain.model.Book
import com.caminaapps.bookworm.domain.repository.GoogleBooksRepository
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
