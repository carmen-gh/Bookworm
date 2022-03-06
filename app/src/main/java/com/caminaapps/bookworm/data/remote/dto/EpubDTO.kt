package com.caminaapps.bookworm.data.remote.dto
import kotlinx.serialization.Serializable

@Serializable
data class EpubDTO(
    val isAvailable: Boolean
)