package com.caminaapps.bookworm.core.data.remote.dto

import kotlinx.serialization.Serializable


@Serializable
data class SearchResultDTO(
    val kind: String? = null,
    val totalItems: Long? = null,
    val items: List<ItemDTO> = emptyList()
)
