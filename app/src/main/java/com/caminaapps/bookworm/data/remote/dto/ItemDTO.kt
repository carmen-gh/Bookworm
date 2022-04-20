package com.caminaapps.bookworm.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ItemDTO(
    val kind: String,
    val id: String,
    val etag: String,
    val selfLink: String,
    val volumeInfo: VolumeInfoDTO,
    val saleInfo: SaleInfoDTO,
    val accessInfo: AccessInfoDTO
)
