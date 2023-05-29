package com.caminaapps.bookworm.testing

import com.caminaapps.bookworm.core.data.database.BookEntity
import com.caminaapps.bookworm.core.model.Book
import kotlinx.datetime.LocalDate

fun testBookEntity(
    id: String = "0",
    title: String = "",
    author: String = "",
    dateAdded: LocalDate = LocalDate(2023, 1, 3),
) = BookEntity(
    id = id,
    title = title,
    subtitle = "",
    author = author,
    publishedDate = "",
    coverUrl = null,
    finishedReading = false,
    isFavourite = false,
    addedToBookshelf = dateAdded
)

fun testBook(
    title: String = "",
    author: String = "",
    dateAdded: LocalDate = LocalDate(2023, 1, 3),
) = Book(
    title = title,
    subtitle = "",
    author = author,
    publishedDate = "",
    coverUrl = null,
    finishedReading = false,
    isFavourite = false,
    addedToBookshelf = dateAdded
)