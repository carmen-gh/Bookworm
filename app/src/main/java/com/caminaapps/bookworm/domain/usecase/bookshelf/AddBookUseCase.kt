package com.caminaapps.bookworm.domain.usecase.bookshelf

import com.caminaapps.bookworm.domain.model.Book
import javax.inject.Inject

class AddBookUseCase @Inject constructor() {
    operator fun invoke(book: Book) {}
}