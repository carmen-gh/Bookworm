package com.caminaapps.bookworm.util.previewParameterProvider

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.caminaapps.bookworm.core.model.Book

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
                    coverUrl = "https://tinyurl.com/yv8t5scy"
                ),
                Book(
                    title = "Android-Programmierung",
                    subtitle = "Der Big Nerd Ranch Guide",
                    author = "Brian Hardy, Bill Phillips",
                    publishedDate = "2012",
                    finishedReading = true,
                    isFavourite = true,
                    coverUrl = "https://tinyurl.com/5aapfne7"
                ),
                Book(
                    title = "Android-Programmierung",
                    subtitle = "Der Big Nerd Ranch Guide",
                    author = "Brian Hardy, Bill Phillips",
                    publishedDate = "2012",
                    finishedReading = true,
                    isFavourite = false,
                    coverUrl = "https://tinyurl.com/5aapfne7"
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
            coverUrl = "https://tinyurl.com/yv8t5scy"
        ),
        Book(
            title = "Android-Programmierung",
            subtitle = "Der Big Nerd Ranch Guide",
            author = "Brian Hardy, Bill Phillips",
            publishedDate = "2012",
            coverUrl = "https://tinyurl.com/5aapfne7"
        ),
        Book(
            title = "Android-Programmierung",
            subtitle = "Der Big Nerd Ranch Guide",
            author = "Brian Hardy, Bill Phillips",
            publishedDate = "2012",
            coverUrl = "https://tinyurl.com/5aapfne7"
        ),
    )
}
