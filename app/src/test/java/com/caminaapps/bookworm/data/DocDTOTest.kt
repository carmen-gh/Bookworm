package com.caminaapps.bookworm.data

import assertk.all
import assertk.assertThat
import assertk.assertions.endsWith
import assertk.assertions.isEmpty
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isNotNull
import assertk.assertions.startsWith
import com.caminaapps.bookworm.core.data.network.dto.DocDTO
import com.caminaapps.bookworm.core.data.network.dto.toBook
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import org.junit.Test

class DocDTOTest {

    @Test
    fun docDTO_mapped_to_book_domain() {
        val docDTO = DocDTO(
            authorName = listOf("Author One", "Author Two"),
            title = "The title of the book",
            publishDate = listOf("July 2020"),
            coverI = 124
        )

        val book = docDTO.toBook()

        assertThat(book.id).isNotNull()
        assertThat(book.subtitle).isEmpty()
        assertThat(book.author).isEqualTo("Author One, Author Two")
        assertThat(book.publishedDate).isEqualTo("July 2020")
        assertThat(book.coverUrl).isNotNull().all {
            startsWith("https://covers.openlibrary.org/b/id/")
            endsWith("-M.jpg")
        }
        assertThat(book.finishedReading).isFalse()
        assertThat(book.isFavourite).isFalse()
        assertThat(book.addedToBookshelf)
            .isEqualTo(Clock.System.todayIn(TimeZone.currentSystemDefault()))
    }
}
