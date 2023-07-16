package com.caminaapps.bookworm.features.searchBookOnline.presentation.searchBarcode

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.caminaapps.bookworm.R
import com.caminaapps.bookworm.core.model.Book
import com.caminaapps.bookworm.core.ui.component.FullScreenLoading
import com.caminaapps.bookworm.core.ui.component.TopAppBarNavigationClose
import com.caminaapps.bookworm.core.ui.theme.BookwormTheme
import com.caminaapps.bookworm.util.previewParameterProvider.BookPreviewParameterProvider

@Composable
fun BookBarcodeResultScreen(
    onCloseScreen: () -> Unit,
    onScanBarcode: () -> Unit,
    viewModel: BookBarcodeResultViewModel = hiltViewModel(),
) {
    Scaffold(
        topBar = {
            TopAppBarNavigationClose(
                title = if (viewModel.uiState.book != null) "Book details" else "",
                onClose = onCloseScreen
            )
        },
    ) { innerPadding ->

        if (viewModel.uiState.isLoading) {
            FullScreenLoading()
        }

        viewModel.uiState.book?.let { book ->
            BookResultContent(
                modifier = Modifier.padding(innerPadding),
                book = book,
                onSaveClick = {
                    viewModel.saveBook()
                    onCloseScreen()
                },
                onSaveAndScanClick = {
                    viewModel.saveBook()
                    onScanBarcode()
                }
            )
        }

        if (viewModel.uiState.book == null && !viewModel.uiState.isLoading) {
            NoBookFoundView(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(vertical = 160.dp)
            )
        }
        if (viewModel.uiState.errorOccurred) {
            Text(
                "Error",
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            )
        }
    }
}

@Composable
fun BookResultContent(
    book: Book,
    onSaveClick: () -> Unit,
    onSaveAndScanClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        SearchBookResultContent(book = book)
        Column {
            Button(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                onClick = onSaveClick
            ) {
                Text(text = stringResource(id = R.string.button_save))
            }
            Button(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                onClick = onSaveAndScanClick
            ) {
                Text(text = stringResource(id = R.string.button_save_scan))
            }
        }
    }
}

@Composable
fun SearchBookResultContent(
    book: Book,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(book.coverUrl)
                .crossfade(true)
                .build(),
            modifier = Modifier.size(width = 215.dp, height = 380.dp),
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

@Preview(showBackground = true)
@Composable
fun SearchBookIsbnContentPreview(
    @PreviewParameter(BookPreviewParameterProvider::class, limit = 2) book: Book,
) {
    BookwormTheme {
        BookResultContent(
            book = book,
            onSaveClick = {},
            onSaveAndScanClick = {},
            modifier = Modifier
        )
    }
}
