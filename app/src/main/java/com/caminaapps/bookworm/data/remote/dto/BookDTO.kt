package com.caminaapps.bookworm.data.remote.dto
import com.caminaapps.bookworm.domain.model.Book
import kotlinx.serialization.Serializable

@Serializable
data class BookDTO(
    val accessInfo: AccessInfoDTO,
    val etag: String,
    val id: String,
    val kind: String,
    val saleInfo: SaleInfoDTO,
    val searchInfo: SearchInfoDTO,
    val selfLink: String,
    val volumeInfo: VolumeInfoDTO
)

fun BookDTO.toBook() : Book {
    return Book(
        title = volumeInfo.title,
        author = volumeInfo.authors.toString(),
        coverUrl = volumeInfo.imageLinks.thumbnail
    )
}