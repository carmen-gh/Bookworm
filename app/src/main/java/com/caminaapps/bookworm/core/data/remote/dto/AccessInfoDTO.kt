package com.caminaapps.bookworm.core.data.remote.dto
import kotlinx.serialization.Serializable

@Serializable
data class AccessInfoDTO(
    val accessViewStatus: String,
    val country: String,
    val embeddable: Boolean,
    val epub: EpubDTO,
    val pdf: PdfDTO,
    val publicDomain: Boolean,
    val quoteSharingAllowed: Boolean,
    val textToSpeechPermission: String,
    val viewability: String,
    val webReaderLink: String
)