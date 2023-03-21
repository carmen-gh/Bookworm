package com.caminaapps.bookworm.testing

import com.caminaapps.bookworm.core.data.database.BookEntity
import kotlinx.datetime.LocalDate

private fun testBookEntity(
    id: String = "0",
    title: String = "",
    dateAdded: LocalDate = LocalDate(2023, 1, 3),
    author: String = ""
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
