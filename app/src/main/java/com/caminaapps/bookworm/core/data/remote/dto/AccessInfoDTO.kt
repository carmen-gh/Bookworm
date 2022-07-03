package com.caminaapps.bookworm.core.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class AccessInfoDTO(
    val accessViewStatus: String? = null,
    val country: String? = null,
    val embeddable: Boolean? = null,
    val epub: EpubDTO? = null,
    val pdf: PdfDTO? = null,
    val publicDomain: Boolean? = null,
    val quoteSharingAllowed: Boolean? = null,
    val textToSpeechPermission: String? = null,
    val viewability: String? = null,
    val webReaderLink: String? = null
)