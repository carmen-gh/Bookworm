package com.caminaapps.bookworm.core.data.remote.dto

import kotlinx.serialization.Serializable


@Serializable
data class SearchResultDTO(
    val kind: String,
    val totalItems: Long,
    val items: List<ItemDTO> = emptyList()
)
