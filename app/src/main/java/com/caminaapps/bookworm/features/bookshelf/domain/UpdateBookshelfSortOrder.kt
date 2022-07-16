package com.caminaapps.bookworm.features.bookshelf.domain

import com.caminaapps.bookworm.core.data.repository.UserPreferencesRepository
import com.caminaapps.bookworm.core.model.BookshelfSortOrder
import javax.inject.Inject

class UpdateBookshelfSortOrderUseCase @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository
) {
    suspend operator fun invoke(sortOrder: BookshelfSortOrder) =
        userPreferencesRepository.saveBookshelfSortOrder(sortOrder)
}
