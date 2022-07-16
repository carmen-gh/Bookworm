package com.caminaapps.bookworm.features.bookshelf.domain

import com.caminaapps.bookworm.core.data.repository.UserPreferencesRepository
import javax.inject.Inject

class GetBookshelfSortOrder @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository
) {
    operator fun invoke() = userPreferencesRepository.bookshelfSortOrder
}
