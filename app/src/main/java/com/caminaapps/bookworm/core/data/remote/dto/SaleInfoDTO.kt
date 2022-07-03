package com.caminaapps.bookworm.core.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class SaleInfoDTO(
    val country: String? = null,
    val isEbook: Boolean? = null,
    val saleability: String? = null
)