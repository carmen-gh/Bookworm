package com.caminaapps.bookworm.presentation.screens.book.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
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
import coil.compose.rememberImagePainter
import com.caminaapps.bookworm.domain.model.Book
import com.caminaapps.bookworm.presentation.components.TopAppBarNavigationUp
import com.caminaapps.bookworm.presentation.previewParameterProvider.BookPreviewParameterProvider
import com.caminaapps.bookworm.presentation.theme.BookwormTheme

@Composable
fun BookScreen(
    viewModel: BookViewModel,
    onUpNavigationClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBarNavigationUp(
                title = "Book details",
                onClick = onUpNavigationClick
            )
        },
    ) {
        // check for ui state
//        BookContent(B)
    }
}


@Composable
fun BookContent(
    book: Book
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberImagePainter(book.coverUrl),
            contentDescription = "book cover",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
//                .fillMaxWidth()
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