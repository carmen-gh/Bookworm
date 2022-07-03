package com.caminaapps.bookworm.core.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class PdfDTO(
    val isAvailable: Boolean? = null
)