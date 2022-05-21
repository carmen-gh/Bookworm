package com.caminaapps.bookworm.features.bookshelf.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
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
import com.caminaapps.bookworm.core.domain.model.Book
import com.caminaapps.bookworm.core.ui.component.TopAppBarNavigationUp
import com.caminaapps.bookworm.core.ui.theme.BookwormTheme
import com.caminaapps.bookworm.core.presentation.previewParameterProvider.BookPreviewParameterProvider

@Composable
fun BookDetailsScreen(
    viewModel: BookViewModel = hiltViewModel(),
    onUpNavigationClick: () -> Unit,
) {
    viewModel.uiState.book?.let { book ->
        Scaffold(
            topBar = {
                TopAppBarNavigationUp(
                    title = book.title,
                    onClick = onUpNavigationClick,
                    actions = {
                        IconButton(onClick = { viewModel.onDeleteBook(book.id) }) {
                            Icon(
                                imageVector = Icons.Filled.Delete,
                                contentDescription = "trash bin"
                            )
                        }
                    }
                )
            },
        ) {
            BookContent(book = book)
        }
    }

    if (viewModel.uiState.bookDeleted) {
        onUpNavigationClick()
    }
}


@Composable
fun BookContent(
    book: Book
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = book.coverUrl,
            contentDescription = "book cover",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .size(width = 315.dp, height = 480.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colors.secondary)
        )
        Spacer(modifier = Modifier.height(42.dp))
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BookContent_Preview(
    @PreviewParameter(BookPreviewParameterProvider::class, limit = 2) book: Book
) {
    BookwormTheme {
        BookContent(book = book)
    }
}
