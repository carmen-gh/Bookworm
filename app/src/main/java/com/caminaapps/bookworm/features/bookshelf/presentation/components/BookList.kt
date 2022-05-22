package com.caminaapps.bookworm.presentation.screens.bookshelf.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.caminaapps.bookworm.core.domain.model.Book
import com.caminaapps.bookworm.util.previewParameterProvider.BooksPreviewParameterProvider
import com.caminaapps.bookworm.core.ui.theme.BookwormTheme


@ExperimentalMaterialApi
@Composable
fun BookList(
    modifier: Modifier = Modifier,
    books: List<Book>,
    onItemClick: (Book) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 80.dp),
//        modifier = modifier.padding(horizontal = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(books) { book ->
            BookListItem(
                title = book.title,
                author = book.author,
                imageUrl = book.coverUrl ?: "",
                isFinished = book.finishedReading,
                onClick = { onItemClick(book) }
            )
        }
    }
}


@ExperimentalMaterialApi
@Preview(
    name = "Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true,
)
@Composable
fun PreviewBookList(
    @PreviewParameter(BooksPreviewParameterProvider::class) books: List<Book>
) {
    BookwormTheme() {
        BookList(books = books, onItemClick = {})
    }
}

@ExperimentalMaterialApi
@Preview(
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun PreviewBookListDark(
    @PreviewParameter(BooksPreviewParameterProvider::class) books: List<Book>
) {
    BookwormTheme {
        BookList(books = books, onItemClick = {})
    }
}
