package com.caminaapps.bookworm.core.data.repository

import com.caminaapps.bookworm.core.model.BookshelfSortOrder
import kotlinx.coroutines.flow.Flow

interface UserPreferencesRepository {
    suspend fun saveBookshelfSortOrder(sortOrder: BookshelfSortOrder)
    val bookshelfSortOrder: Flow<BookshelfSortOrder>
}
