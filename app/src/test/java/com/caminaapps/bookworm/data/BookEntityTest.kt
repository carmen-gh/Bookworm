package com.caminaapps.bookworm.data

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isNull
import assertk.assertions.isTrue
import com.caminaapps.bookworm.core.data.database.BookEntity
import com.caminaapps.bookworm.core.data.database.toBook
import com.caminaapps.bookworm.core.data.database.toBookEntity
import com.caminaapps.bookworm.core.model.Book
import kotlinx.datetime.LocalDate
import org.junit.Test

class BookEntityTest {

    @Test
    fun bookEntity_can_be_mapped_to_bookDomain() {
        val bookEntity = BookEntity(
            id = "an id",
            title = "My title",
            subtitle = "my subtitle",
            author = "awesome author",
            publishedDate = "23.01.2023",
            coverUrl = null,
            finishedReading = true,
            isFavourite = false,
            addedToBookshelf = LocalDate.fromEpochDays(19427)
        )

        val book = bookEntity.toBook()

        assertThat(book.id).isEqualTo("an id")
        assertThat(book.subtitle).isEqualTo("my subtitle")
        assertThat(book.author).isEqualTo("awesome author")
        assertThat(book.publishedDate).isEqualTo("23.01.2023")
        assertThat(book.coverUrl).isNull()
        assertThat(book.finishedReading).isTrue()
        assertThat(book.isFavourite).isFalse()
        assertThat(book.addedToBookshelf).isEqualTo(LocalDate.fromEpochDays(19427))
    }

    @Test
    fun bookDomain_can_be_mapped_bookEntity() {
        val book = Book(
            id = "an id",
            title = "My title",
            subtitle = "my subtitle",
            author = "awesome author",
            publishedDate = "23.01.2023",
            coverUrl = null,
            finishedReading = true,
            isFavourite = false,
            addedToBookshelf = LocalDate.fromEpochDays(19427)
        )

        val bookEntity = book.toBookEntity()

        assertThat(bookEntity.id).isEqualTo("an id")
        assertThat(bookEntity.subtitle).isEqualTo("my subtitle")
        assertThat(bookEntity.author).isEqualTo("awesome author")
        assertThat(bookEntity.publishedDate).isEqualTo("23.01.2023")
        assertThat(bookEntity.coverUrl).isNull()
        assertThat(bookEntity.finishedReading).isTrue()
        assertThat(bookEntity.isFavourite).isFalse()
        assertThat(bookEntity.addedToBookshelf).isEqualTo(LocalDate.fromEpochDays(19427))
    }
}
