package com.caminaapps.bookworm.core.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.caminaapps.bookworm.core.model.Book
import java.time.OffsetDateTime

@Entity(
    tableName = "book"
)
data class BookEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val subtitle: String,
    val author: String,
    @ColumnInfo(name = "published_date")
    val publishedDate: String,
    @ColumnInfo(name = "cover_url")
    val coverUrl: String?,
    @ColumnInfo(name = "finished_reading")
    val finishedReading: Boolean,
    @ColumnInfo(name = "favourite")
    val isFavourite: Boolean,
    @ColumnInfo(name = "added_date")
    val addedToBookshelf: OffsetDateTime
)

fun BookEntity.toBook() = Book(
    id = id,
    title = title,
    subtitle = subtitle,
    author = author,
    publishedDate = publishedDate,
    coverUrl = coverUrl,
    finishedReading = finishedReading,
    isFavourite = isFavourite,
    addedToBookshelf = addedToBookshelf
)

fun Book.toBookEntity() = BookEntity(
    id = id,
    title = title,
    subtitle = subtitle,
    author = author,
    publishedDate = publishedDate,
    coverUrl = coverUrl,
    finishedReading = finishedReading,
    isFavourite = isFavourite,
    addedToBookshelf = addedToBookshelf
)
