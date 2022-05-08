package com.caminaapps.bookworm.core.data.remote.dto

import com.caminaapps.bookworm.core.model.Book
import kotlinx.serialization.Serializable

@Serializable
data class VolumeInfoDTO(
    val title: String,
    val subtitle: String? = null,
    val authors: List<String>? = null,
    val publishedDate: String? = null,
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

fun VolumeInfoDTO.toBook(): Book {
    return Book(
        title = title,
        subtitle = subtitle ?: "",
        author = authors?.joinToString(separator = ", ") ?: "",
        publishedDate = publishedDate ?: "",
        coverUrl = imageLinks?.thumbnail?.replace("http", "https")
    )
}
