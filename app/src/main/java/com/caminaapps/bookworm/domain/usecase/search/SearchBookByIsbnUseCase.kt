package com.caminaapps.bookworm.domain.usecase.search

import com.caminaapps.bookworm.domain.repository.GoogleBooksRepository
import javax.inject.Inject

class SearchBookByIsbnUseCase @Inject constructor(
    private val googleBooksRepository: GoogleBooksRepository,
) {
    operator fun invoke(): String {
        return "test"
    }
}