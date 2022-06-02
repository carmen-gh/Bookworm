package com.caminaapps.bookworm.features.bookshelf.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

    val uiState: BookDetailsUiState by viewModel.uiState.collectAsState()

    when (uiState) {
        is Loading -> FullScreenLoading()
        is NotFound -> onUpNavigationClick()
        is Error -> TODO()
        is Success -> {
            val book = (uiState as Success).book
            BookContent(
                book = book,
                onUpNavigationClick = onUpNavigationClick,
                onDeleteBookClick = { viewModel.onDeleteBook(bookId = book.id) }
            )
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
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBarNavigationUp(
                title = book.title,
                onClick = onUpNavigationClick,
                actions = {
                    IconButton(onClick = { onDeleteBookClick() }) {
                        Icon(
                            imageVector = Icons.Outlined.Delete,
                            contentDescription = "trash bin"
                        )
                    }
                }
            )
        },
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = book.coverUrl,
                contentDescription = "book cover",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .size(width = 315.dp, height = 480.dp)
            )
            Spacer(modifier = Modifier.height(42.dp))
            Text(
                text = book.title,
                style = MaterialTheme.typography.h5,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis,
            )
            if (book.subtitle.isNotBlank()) {
                Text(
                    text = book.subtitle,
                    style = MaterialTheme.typography.h6
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
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
