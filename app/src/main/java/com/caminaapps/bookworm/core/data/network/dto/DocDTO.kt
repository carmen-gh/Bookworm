package com.caminaapps.bookworm.core.data.network.dto

import com.caminaapps.bookworm.core.model.Book
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DocDTO(
    @SerialName("author_facet")
    val authorFacet: List<String> = emptyList(),
    @SerialName("author_key")
    val authorKey: List<String> = emptyList(),
    @SerialName("author_name")
    val authorName: List<String> = emptyList(),
    @SerialName("cover_edition_key")
    val coverEditionKey: String? = null,
    @SerialName("cover_i")
    val coverI: Int? = null,
    @SerialName("ebook_count_i")
    val ebookCountI: Int? = null,
    @SerialName("edition_count")
    val editionCount: Int? = null,
    @SerialName("edition_key")
    val editionKey: List<String> = emptyList(),
    @SerialName("first_publish_year")
    val firstPublishYear: Int? = null,
    @SerialName("has_fulltext")
    val hasFulltext: Boolean? = null,
    val isbn: List<String> = emptyList(),
    val key: String? = null,
    @SerialName("last_modified_i")
    val lastModifiedI: Int? = null,
    @SerialName("publish_date")
    val publishDate: List<String> = emptyList(),
    @SerialName("publish_year")
    val publishYear: List<Int> = emptyList(),
    val publisher: List<String> = emptyList(),
    @SerialName("publisher_facet")
    val publisherFacet: List<String> = emptyList(),
    val seed: List<String> = emptyList(),
    val title: String? = null,
    @SerialName("title_suggest")
    val titleSuggest: String? = null,
    val type: String? = null,
    @SerialName("_version_")
    val version: Long? = null
)

fun DocDTO.asBook(): Book {
    return Book(
        title = title ?: "",
        subtitle = "",
        author = authorName.joinToString(separator = ", "),
        publishedDate = publishDate.firstOrNull() ?: "",
        coverUrl = "https://covers.openlibrary.org/b/id/${coverI}-M.jpg"
    )
}
