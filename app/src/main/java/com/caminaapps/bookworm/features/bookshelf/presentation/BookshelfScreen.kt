package com.caminaapps.bookworm.features.bookshelf.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.DropdownMenu
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FabPosition
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.caminaapps.bookworm.R
import com.caminaapps.bookworm.core.model.Book
import com.caminaapps.bookworm.core.model.BookshelfSortOrder
import com.caminaapps.bookworm.core.ui.component.TrackedScreen
import com.caminaapps.bookworm.features.bookshelf.presentation.BookshelfUiState.Error
import com.caminaapps.bookworm.features.bookshelf.presentation.BookshelfUiState.Loading
import com.caminaapps.bookworm.features.bookshelf.presentation.BookshelfUiState.Success
import com.caminaapps.bookworm.features.bookshelf.presentation.components.AddBookFloatingActionButton
import com.caminaapps.bookworm.features.bookshelf.presentation.components.BookList
import com.google.accompanist.permissions.ExperimentalPermissionsApi

@ExperimentalComposeUiApi
@ExperimentalPermissionsApi
@Composable
fun BookshelfScreen(
    onScanBarcode: () -> Unit,
    onSearchOnline: () -> Unit,
    onEnterBook: () -> Unit,
    onBook: (id: Book) -> Unit, // change to id: bookId
    onSearchList: () -> Unit,
    viewModel: BookshelfViewModel = hiltViewModel()
) {
    TrackedScreen(name = "Bookshelf")
    var sortingMenuExpanded by remember { mutableStateOf(false) }
    val uiState: BookshelfUiState by viewModel.uiState.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.TopEnd)
            .padding(top = 24.dp, end = 8.dp)
    ) {
        SortingDropDownMenu(
            expanded = sortingMenuExpanded,
            selectedItem = (uiState as? Success)?.sortOrder,
            onSelectedItem = {
                sortingMenuExpanded = false
                viewModel.updateSortOrder(it)
            },
            onDismissRequest = { sortingMenuExpanded = false }
        )
    }

    // TODO() move out to own component
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { onSearchList() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_search_42),
                            contentDescription = stringResource(id = R.string.button_search),
                            modifier = Modifier.padding(14.dp)
                        )
                    }
                },
                title = { Text(text = "") },
                actions = {
                    IconButton(onClick = { sortingMenuExpanded = !sortingMenuExpanded }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_sort_48),
                            contentDescription = null,
                            modifier = Modifier.padding(12.dp)
                        )
                    }
                }
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            AddBookFloatingActionButton(
                onManual = onEnterBook,
                onSearch = onSearchOnline,
                onScan = onScanBarcode
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (uiState) {
                is Success -> BooksContent(
                    books = (uiState as Success).books,
                    onBookClick = onBook
                )

                is Loading -> CircularProgressIndicator()
                is Error -> Text(stringResource(R.string.error_general_text))
            }
        }
    }
}

@Composable
fun SortingDropDownMenu(
    expanded: Boolean,
    selectedItem: BookshelfSortOrder?,
    onSelectedItem: (BookshelfSortOrder) -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismissRequest,
        modifier = modifier
    ) {
        Column(Modifier.selectableGroup()) {
            BookshelfSortOrder.valuesAsList().forEach { item ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .heightIn(min = 56.dp)
                        .selectable(
                            selected = item == selectedItem,
                            onClick = { onSelectedItem(item) },
                            role = Role.RadioButton
                        )
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = item == selectedItem,
                        onClick = null // null recommended for accessibility with screenreaders
                    )
                    Text(
                        text = stringResource(id = item.toStringResId()),
                        style = MaterialTheme.typography.body1.merge(),
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BooksContent(
    books: List<Book>,
    onBookClick: (id: Book) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        if (books.isEmpty()) {
            Text("add some books to get started")
        } else {
            BookList(
                books = books,
                onItemClick = onBookClick,
            )
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview
@Composable
fun BookshelfPreview() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "") },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_sort_48),
                            contentDescription = null,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            )
        },
    ) {}
}
