package com.caminaapps.bookworm.searchBookOnline.presentation.result

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.caminaapps.bookworm.R
import com.caminaapps.bookworm.domain.model.Book
import com.caminaapps.bookworm.presentation.components.FullScreenLoading
import com.caminaapps.bookworm.presentation.components.TopAppBarNavigationClose
import com.caminaapps.bookworm.presentation.previewParameterProvider.BookPreviewParameterProvider
import com.caminaapps.bookworm.presentation.theme.BookwormTheme
import com.caminaapps.bookworm.searchBookOnline.presentation.common.SearchBookResultContent


@Composable
fun BookResultScreen(
    viewModel: BookResultViewModel = hiltViewModel(),
    onCloseScreenClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBarNavigationClose(
                title = "Book details",
                onClick = onCloseScreenClick
            )
        },
    ) {

        if (viewModel.uiState.isLoading) {
            FullScreenLoading()
        }

        viewModel.uiState.book?.let { book ->
            BookResultContent(
                modifier = Modifier.padding(24.dp),
                book = book,
                onSaveClick = viewModel::saveBook
            )
        }

        if (viewModel.uiState.book == null && !viewModel.uiState.isLoading) {
            NoBookFoundView(modifier = Modifier.fillMaxSize())
        }

        if (viewModel.uiState.errorOcured) {
            Text("Error", modifier = Modifier.fillMaxSize())
        }

    }
}


@Composable
fun BookResultContent(
    modifier: Modifier,
    book: Book,
    onSaveClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        SearchBookResultContent(book = book)
        Button(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            onClick = onSaveClick
        ) {
            Text(text = stringResource(id = R.string.button_save))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchBookIsbnContentPreview(
    @PreviewParameter(BookPreviewParameterProvider::class, limit = 2) book: Book
) {
    BookwormTheme {
        BookResultContent(book = book, onSaveClick = {}, modifier = Modifier)
    }
}