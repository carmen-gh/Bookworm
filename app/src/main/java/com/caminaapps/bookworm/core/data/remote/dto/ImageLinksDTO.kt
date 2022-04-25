package com.caminaapps.bookworm.core.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ImageLinksDTO(
    val smallThumbnail: String,
    val thumbnail: String
)