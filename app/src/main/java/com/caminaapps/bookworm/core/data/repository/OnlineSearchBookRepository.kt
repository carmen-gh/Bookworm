package com.caminaapps.bookworm.core.data.repository

import com.caminaapps.bookworm.core.model.Book

interface OnlineSearchBookRepository {
    suspend fun getBookByISBN(isbn: String): Book?
    suspend fun getBooksByTitle(title: String): List<Book>
}
