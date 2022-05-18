package com.caminaapps.bookworm.core.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ReadingModesDTO(
    val image: Boolean? = null,
    val text: Boolean? = null
)