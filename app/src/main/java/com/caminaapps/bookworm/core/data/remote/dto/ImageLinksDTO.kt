package com.caminaapps.bookworm.core.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ImageLinksDTO(
    val smallThumbnail: String? = null,
    val thumbnail: String? = null
)