package com.caminaapps.bookworm.features.searchBookOnline.presentation.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.caminaapps.bookworm.R
import com.caminaapps.bookworm.core.domain.model.Book
import com.caminaapps.bookworm.core.presentation.previewParameterProvider.BookPreviewParameterProvider
import com.caminaapps.bookworm.core.ui.theme.BookwormTheme

@Composable
fun SearchBookResultContent(
    book: Book
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
//        Image(
//            painter = rememberImagePainter(book.coverUrl),
//            contentDescription = book.title,
//            contentScale = ContentScale.FillWidth,
//            modifier = Modifier
//                .size(width = 215.dp, height = 380.dp)
//                .clip(RoundedCornerShape(16.dp))
//                .background(MaterialTheme.colors.secondary)
//
//        )
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(book.coverUrl)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.ic_baseline_book_24),
            contentDescription = book.title,
            contentScale = ContentScale.Crop,
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
        Text(text = book.author)
        Text(text = book.publishedDate)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SearchBookResultContentPreview(
    @PreviewParameter(BookPreviewParameterProvider::class, limit = 2) book: Book
) {
    BookwormTheme {
        SearchBookResultContent(book = book)
    }
}
