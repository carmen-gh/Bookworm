package com.caminaapps.bookworm.features.bookshelf.domain

import com.caminaapps.bookworm.core.domain.model.Book
import javax.inject.Inject

class AddBookUseCase @Inject constructor() {
    operator fun invoke(book: Book) {}
}