package com.caminaapps.bookworm.domain.repository

import com.caminaapps.bookworm.domain.model.Book

interface GoogleBooksRepository {
    suspend fun getBookByISBN(isbn: String) : Book?
    suspend fun getBooksByTitle(title: String) : List<Book>
}