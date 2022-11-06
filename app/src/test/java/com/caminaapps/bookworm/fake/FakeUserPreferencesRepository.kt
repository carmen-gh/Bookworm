package com.caminaapps.bookworm.fake

import com.caminaapps.bookworm.core.data.repository.UserPreferencesRepository
import com.caminaapps.bookworm.core.model.BookshelfSortOrder
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow

class FakeUserPreferencesRepository : UserPreferencesRepository {

    private var flow = MutableSharedFlow<BookshelfSortOrder>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    var returnError: Boolean = false

    suspend fun send(sortOrder: BookshelfSortOrder) = flow.emit(sortOrder)

    override suspend fun saveBookshelfSortOrder(sortOrder: BookshelfSortOrder) {
        flow.tryEmit(sortOrder)
    }

    override val bookshelfSortOrder: Flow<BookshelfSortOrder> =
        if (returnError) {
            flow { throw IllegalStateException("error") }
        } else {
            flow
        }
}
