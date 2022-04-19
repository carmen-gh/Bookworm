package com.caminaapps.bookworm.data.remote

import com.caminaapps.bookworm.data.remote.dto.VolumeInfoDTO
import com.caminaapps.bookworm.domain.model.Book


fun VolumeInfoDTO.toBook(): Book {
    return Book(
        title = title,
        subtitle = subtitle ?: "",
        author = authors?.joinToString(separator = ", ") ?: "",
        publishedDate = publishedDate,
        coverUrl = imageLinks?.thumbnail?.replace("http", "https")
    )
}