package com.caminaapps.bookworm.util.previewParameterProvider

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.caminaapps.bookworm.core.domain.model.Book


class BooksPreviewParameterProvider : PreviewParameterProvider<List<Book>> {
    override val values: Sequence<List<Book>>
        get() = sequenceOf(
            listOf(
                Book(
                    title = "Die 7 Wege zur Effektivität - Workbook",
                    subtitle = "this is a subtitle",
                    author = "Stephen R. Covey",
                    publishedDate = "2016",
                    finishedReading = false,
                    isFavourite = true,
                    coverUrl = "https://books.google.com/books/content?id=f94S3a1SzvoC&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api"
                ),
                Book(
                    title = "Android-Programmierung",
                    subtitle = "Der Big Nerd Ranch Guide",
                    author = "Brian Hardy, Bill Phillips",
                    publishedDate = "2012",
                    finishedReading = true,
                    isFavourite = true,
                    coverUrl = "http://books.google.com/books/content?id=aiyrtgAACAAJ&printsec=frontcover&img=1&zoom=5&source=gbs_api"
                ),
                Book(
                    title = "Android-Programmierung",
                    subtitle = "Der Big Nerd Ranch Guide",
                    author = "Brian Hardy, Bill Phillips",
                    publishedDate = "2012",
                    finishedReading = true,
                    isFavourite = false,
                    coverUrl = "http://books.google.com/books/content?id=aiyrtgAACAAJ&printsec=frontcover&img=1&zoom=5&source=gbs_api"
                ),
            )
        )
}


class BookPreviewParameterProvider : PreviewParameterProvider<Book> {
    override val values = sequenceOf(
        Book(
            title = "Die 7 Wege zur Effektivität - Workbook",
            subtitle = "",
            author = "Stephen R. Covey",
            publishedDate = "2016",
            coverUrl = "https://books.google.com/books/content?id=f94S3a1SzvoC&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api"
        ),
        Book(
            title = "Android-Programmierung",
            subtitle = "Der Big Nerd Ranch Guide",
            author = "Brian Hardy, Bill Phillips",
            publishedDate = "2012",
            coverUrl = "http://books.google.com/books/content?id=aiyrtgAACAAJ&printsec=frontcover&img=1&zoom=5&source=gbs_api"
        ),
        Book(
            title = "Android-Programmierung",
            subtitle = "Der Big Nerd Ranch Guide",
            author = "Brian Hardy, Bill Phillips",
            publishedDate = "2012",
            coverUrl = "http://books.google.com/books/content?id=aiyrtgAACAAJ&printsec=frontcover&img=1&zoom=5&source=gbs_api"
        ),
    )
}
