package com.caminaapps.bookworm.core.domain.repository

import com.caminaapps.bookworm.core.domain.model.Book

interface OnlineSearchBookRepository {
    suspend fun getBookByISBN(isbn: String): Book?
    suspend fun getBooksByTitle(title: String): List<Book>
}