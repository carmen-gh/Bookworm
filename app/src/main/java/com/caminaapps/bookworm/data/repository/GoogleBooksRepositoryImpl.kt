package com.caminaapps.bookworm.data.repository

import com.caminaapps.bookworm.data.remote.GoogleBooksApi
import com.caminaapps.bookworm.data.remote.dto.toBook
import com.caminaapps.bookworm.domain.model.Book
import com.caminaapps.bookworm.domain.repository.GoogleBooksRepository
import javax.inject.Inject

class GoogleBooksRepositoryImpl @Inject constructor(
    private val googleBooksApi: GoogleBooksApi,
) : GoogleBooksRepository {

    override suspend fun getBookByISBN(isbn: String) : Book? =
       googleBooksApi.getBooks(GoogleBooksApi.parameterISBN(isbn)).firstOrNull()?.toBook()

    override suspend fun getBooksByTitle(title: String) : List<Book> {
        return if (title.isBlank()) {
            emptyList<Book>()
        } else {
            val searchResult = googleBooksApi.getBooks(GoogleBooksApi.parameterTitle(title))
            searchResult.map { it.toBook() }
        }
    }

}