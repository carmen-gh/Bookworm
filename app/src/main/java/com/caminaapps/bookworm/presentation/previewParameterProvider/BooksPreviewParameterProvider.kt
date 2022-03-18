package com.caminaapps.bookworm.presentation.previewParameterProvider

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.caminaapps.bookworm.domain.model.Book


class BooksPreviewParameterProvider : PreviewParameterProvider<List<Book>> {
    override val values: Sequence<List<Book>>
        get() = sequenceOf(
            listOf(
                Book(
                    title = "Die 7 Wege zur Effektivität - Workbook",
                    author = "Stephen R. Covey",
                    coverUrl = "https://books.google.com/books/content?id=f94S3a1SzvoC&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api"
                ),
                Book(
                    title = "Die 7 Wege zur Effektivität - Workbook",
                    author = "Stephen R. Covey",
                    coverUrl = "https://books.google.com/books/content?id=f94S3a1SzvoC&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api"
                ),
                Book(
                    title = "Die 7 Wege zur Effektivität - Workbook",
                    author = "Stephen R. Covey",
                    coverUrl = "https://books.google.com/books/content?id=f94S3a1SzvoC&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api"
                )
            )
        )
}