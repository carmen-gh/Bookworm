package com.caminaapps.bookworm.core.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.caminaapps.bookworm.core.model.BookshelfSortOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserPreferencesRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : UserPreferencesRepository {

    private val bookshelfSortOrderKey = stringPreferencesKey("bookshelf_sort_order")

    override suspend fun saveBookshelfSortOrder(sortOrder: BookshelfSortOrder) {
        dataStore.edit { settings ->
            settings[bookshelfSortOrderKey] = sortOrder.name
        }
    }

    override val bookshelfSortOrder: Flow<BookshelfSortOrder> = dataStore.data
        .map { preferences ->
            val sortOrder = preferences[bookshelfSortOrderKey] ?: BookshelfSortOrder.getDefault().name
            BookshelfSortOrder.valueOf(sortOrder)
        }

}
