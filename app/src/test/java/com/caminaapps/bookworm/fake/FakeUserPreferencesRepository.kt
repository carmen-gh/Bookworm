package com.caminaapps.bookworm.fake

import com.caminaapps.bookworm.core.data.repository.UserPreferencesRepository
import com.caminaapps.bookworm.core.model.BookshelfSortOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class FakeUserPreferencesRepository : UserPreferencesRepository {

    private val flow = MutableSharedFlow<BookshelfSortOrder>()
    suspend fun send(sortOrder: BookshelfSortOrder) = flow.emit(sortOrder)

    override suspend fun saveBookshelfSortOrder(sortOrder: BookshelfSortOrder) {
        flow.emit(sortOrder)
    }

    override val bookshelfSortOrder: Flow<BookshelfSortOrder> = flow

}
