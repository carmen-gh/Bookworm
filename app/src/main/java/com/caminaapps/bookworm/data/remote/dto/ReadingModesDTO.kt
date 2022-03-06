package com.caminaapps.bookworm.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ReadingModesDTO(
    val image: Boolean,
    val text: Boolean
)