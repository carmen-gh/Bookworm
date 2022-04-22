package com.caminaapps.bookworm.domain.model

import java.util.UUID

data class Book(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val subtitle: String,
    val author: String,
    val publishedDate: String,
    val coverUrl: String?,
    val finishedReading: Boolean = false,
    val isFavourite: Boolean = false
)
