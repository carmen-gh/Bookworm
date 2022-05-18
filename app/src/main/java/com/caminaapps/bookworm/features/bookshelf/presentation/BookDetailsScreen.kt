package com.caminaapps.bookworm.features.bookshelf.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.caminaapps.bookworm.core.model.Book
import com.caminaapps.bookworm.core.ui.component.FullScreenLoading
import com.caminaapps.bookworm.core.ui.component.TopAppBarNavigationUp
import com.caminaapps.bookworm.core.ui.theme.BookwormTheme
import com.caminaapps.bookworm.features.bookshelf.presentation.BookDetailsUiState.*
import com.caminaapps.bookworm.util.previewParameterProvider.BookPreviewParameterProvider

@Composable
fun BookDetailsScreen(
    viewModel: BookViewModel = hiltViewModel(),
    onUpNavigationClick: () -> Unit,
) {
    viewModel.uiState.book?.let { book ->
        Scaffold(
            backgroundColor = MaterialTheme.colors.surface,
            topBar = {
                TopAppBarNavigationUp(
                    title = book.title,
                    onClick = onUpNavigationClick
                ) {
                    IconButton(onClick = { viewModel.onDeleteBook(book.id) }) {
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = "trash bin"
                        )
                    }
                }
            },
        ) {
            BookContent(book = book)
        }
    }

}


@Composable
fun BookContent(
    modifier: Modifier = Modifier,
    book: Book,
    onUpNavigationClick: () -> Unit,
    onDeleteBookClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (!book.coverUrl.isNullOrBlank()) {
            AsyncImage(
                model = book.coverUrl,
                contentDescription = "book cover",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(width = 315.dp, height = 480.dp)
                    .clip(RoundedCornerShape(16.dp))
            )
        }

        Spacer(modifier = Modifier.height(42.dp))
        Text(
            text = book.title,
            style = MaterialTheme.typography.h4,
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
        )
        if (book.subtitle.isNotBlank()) {
            Text(
                text = book.title,
                style = MaterialTheme.typography.h4,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis,
            )
            if (book.subtitle.isNotBlank()) {
                Text(
                    text = book.subtitle,
                    style = MaterialTheme.typography.h6
                )
            }
            Text(text = book.author)
            Text(text = book.publishedDate)
        }
        // Reading status
        // isFavourite book
        // tags
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BookContent_Preview(
    @PreviewParameter(BookPreviewParameterProvider::class, limit = 2) book: Book
) {
    BookwormTheme {
        BookContent(book = book, onDeleteBookClick = {}, onUpNavigationClick = {})
    }
}
