package com.caminaapps.bookworm.core.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ItemDTO(
    val kind: String? = null,
    val id: String? = null,
    val etag: String? = null,
    val selfLink: String? = null,
    val volumeInfo: VolumeInfoDTO? = null,
    val saleInfo: SaleInfoDTO? = null,
    val accessInfo: AccessInfoDTO? = null
)
