package com.caminaapps.bookworm.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ImageLinksDTO(
    val smallThumbnail: String,
    val thumbnail: String
)