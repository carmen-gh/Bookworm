package com.caminaapps.bookworm.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class VolumeInfoDTO(
    val title: String,
    val subtitle: String? = null,
    val authors: List<String>? = null,
    val publishedDate: String,
    val industryIdentifiers: List<IndustryIdentifierDTO>? = null,
    val readingModes: ReadingModesDTO? = null,
    val pageCount: Long? = null,
    val printType: String? = null,
    val maturityRating: String? = null,
    val allowAnonLogging: Boolean? = null,
    val contentVersion: String? = null,
    val imageLinks: ImageLinksDTO? = null,
    val language: String? = null,
    val previewLink: String? = null,
    val infoLink: String? = null,
    val canonicalVolumeLink: String? = null
)

