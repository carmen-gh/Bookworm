package com.caminaapps.bookworm.features.enterBook

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.caminaapps.bookworm.R
import com.caminaapps.bookworm.core.model.Book
import com.caminaapps.bookworm.core.ui.component.TopAppBarNavigationClose
import com.caminaapps.bookworm.core.ui.component.TrackedScreen
import com.caminaapps.bookworm.core.ui.theme.BookwormTheme
import com.caminaapps.bookworm.features.enterBook.EnterBookUiState.BookSaved
import com.caminaapps.bookworm.features.enterBook.EnterBookUiState.ErrorTitleMissing

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun EnterBookScreen(
    onUpNavigationClick: () -> Unit,
    viewModel: EnterBookViewModel = hiltViewModel(),
) {
    TrackedScreen(name = "Enter book")

    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    if (uiState.value is BookSaved) {
        onUpNavigationClick()
    }

    EnterBookContent(
        modifier = Modifier.fillMaxWidth(),
        onClose = onUpNavigationClick,
        onSave = { viewModel.saveBook(it) },
        onTitleValueChange = viewModel::titleInputChanged,
        showTitleError = uiState.value is ErrorTitleMissing
    )
}

@Composable
fun EnterBookContent(
    onClose: () -> Unit,
    onSave: (Book) -> Unit,
    onTitleValueChange: () -> Unit,
    showTitleError: Boolean,
    modifier: Modifier = Modifier,
) {
    var title by rememberSaveable { mutableStateOf("") }
    var subtitle by rememberSaveable { mutableStateOf("") }
    var author by rememberSaveable { mutableStateOf("") }
    var published by rememberSaveable { mutableStateOf("") }
    var isFavourite by rememberSaveable { mutableStateOf(false) }
    var isFinished by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBarNavigationClose(
                title = stringResource(R.string.book_enter_title),
                onClose = { onClose() }
            ) {
                TextButton(onClick = {
                    onSave(
                        Book(
                            title = title,
                            subtitle = subtitle,
                            author = author,
                            publishedDate = published,
                            isFavourite = isFavourite,
                            finishedReading = isFinished,
                            coverUrl = null
                        )
                    )
                }) {
                    Text(
                        text = stringResource(id = R.string.button_save),
                        color = MaterialTheme.colors.onPrimary
                    )
                }
            }
        }
    ) { innerPadding ->
        ConstraintLayout(modifier = modifier.padding(innerPadding)) {
            val (inputColumn, checkboxColumn) = createRefs()

            Column(
                modifier = Modifier.constrainAs(inputColumn) {
                    top.linkTo(parent.top, margin = 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = title,
                    label = { Text(text = stringResource(R.string.book_title)) },
                    isError = showTitleError,
                    onValueChange = {
                        onTitleValueChange()
                        title = it
                    }
                )
                if (showTitleError) {
                    Text(
                        text = "Title is missing",
                        color = MaterialTheme.colors.error,
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
                OutlinedTextField(
                    value = subtitle,
                    label = { Text(text = stringResource(R.string.book_subtitle)) },
                    onValueChange = { subtitle = it }
                )
                OutlinedTextField(
                    value = author,
                    label = { Text(text = stringResource(R.string.book_author)) },
                    onValueChange = { author = it }
                )
                OutlinedTextField(
                    value = published,
                    label = { Text(text = stringResource(R.string.book_published)) },
                    onValueChange = { published = it }
                )
            }
            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .constrainAs(checkboxColumn) {
                        top.linkTo(inputColumn.bottom, margin = 16.dp)
                        start.linkTo(inputColumn.start)
                        end.linkTo(inputColumn.end)
                        width = Dimension.fillToConstraints
                    },
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = stringResource(R.string.book_favourite),
                        modifier = Modifier.weight(1.0F)
                    )
                    Switch(checked = isFavourite, onCheckedChange = { isFavourite = it })
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = stringResource(R.string.book_finished),
                        modifier = Modifier.weight(1.0F)
                    )
                    Switch(checked = isFinished, onCheckedChange = { isFinished = it })
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddBookScreenPreview() {
    BookwormTheme {
        EnterBookContent(
            onClose = {},
            onSave = {},
            onTitleValueChange = {},
            showTitleError = true,
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        )
    }
}
