package com.caminaapps.bookworm.core.data.network.dto


import kotlinx.serialization.Serializable

@Serializable
data class SearchResultDTO(
    val docs: List<DocDTO> = emptyList(),
    val numFound: Int? = null,
    val numFoundExact: Boolean? = null,
    val q: String? = null,
    val start: Int? = null
)
