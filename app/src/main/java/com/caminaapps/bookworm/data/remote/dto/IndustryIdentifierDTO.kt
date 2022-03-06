package com.caminaapps.bookworm.data.remote.dto
import kotlinx.serialization.Serializable

@Serializable
data class IndustryIdentifierDTO(
    val identifier: String,
    val type: String
)