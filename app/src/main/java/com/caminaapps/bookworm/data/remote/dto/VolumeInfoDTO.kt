package com.caminaapps.bookworm.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class VolumeInfoDTO(
    val allowAnonLogging: Boolean,
    val authors: List<String>,
    val averageRating: Int,
    val canonicalVolumeLink: String,
    val contentVersion: String,
    val description: String,
    val imageLinks: ImageLinksDTO,
    val industryIdentifiers: List<IndustryIdentifierDTO>,
    val infoLink: String,
    val language: String,
    val maturityRating: String,
    val pageCount: Int,
    val previewLink: String,
    val printType: String,
    val publishedDate: String,
    val publisher: String,
    val ratingsCount: Int,
    val readingModes: ReadingModesDTO,
    val subtitle: String,
    val title: String
)