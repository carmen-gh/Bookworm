package com.caminaapps.bookworm.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class PdfDTO(
    val isAvailable: Boolean
)