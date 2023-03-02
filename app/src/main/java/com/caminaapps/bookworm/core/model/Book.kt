package com.caminaapps.bookworm.core.model

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import java.util.*

typealias BookId = String

data class Book(
    val id: BookId = UUID.randomUUID().toString(),
    val title: String,
    val subtitle: String,
    val author: String,
    val publishedDate: String,
    val coverUrl: String?,
    val finishedReading: Boolean = false,
    val isFavourite: Boolean = false,
    val addedToBookshelf: Instant = Clock.System.now()
)
