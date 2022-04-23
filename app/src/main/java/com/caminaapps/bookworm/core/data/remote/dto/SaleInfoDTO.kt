package com.caminaapps.bookworm.core.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class SaleInfoDTO(
    val country: String,
    val isEbook: Boolean,
    val saleability: String
)