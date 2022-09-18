package com.caminaapps.bookworm.features.bookshelf.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.runtime.Composable
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
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.caminaapps.bookworm.core.model.Book
import com.caminaapps.bookworm.core.ui.component.FullScreenLoading
import com.caminaapps.bookworm.core.ui.component.TopAppBarNavigationUp
import com.caminaapps.bookworm.core.ui.component.TrackedScreen
import com.caminaapps.bookworm.core.ui.theme.BookwormTheme
import com.caminaapps.bookworm.features.bookshelf.presentation.BookDetailsUiState.Error
import com.caminaapps.bookworm.features.bookshelf.presentation.BookDetailsUiState.Loading
import com.caminaapps.bookworm.features.bookshelf.presentation.BookDetailsUiState.NotFound
import com.caminaapps.bookworm.features.bookshelf.presentation.BookDetailsUiState.Success
import com.caminaapps.bookworm.util.previewParameterProvider.BookPreviewParameterProvider

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun BookDetailsScreen(
    viewModel: BookViewModel = hiltViewModel(),
    onUpNavigationClick: () -> Unit,
) {
    TrackedScreen(name = "Book details")
    val uiState: BookDetailsUiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (uiState) {
        is Loading -> FullScreenLoading()
        is NotFound -> onUpNavigationClick()
        is Error -> {}
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
    book: Book,
    onUpNavigationClick: () -> Unit,
    onDeleteBookClick: () -> Unit,
    modifier: Modifier = Modifier,
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
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth(),
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
