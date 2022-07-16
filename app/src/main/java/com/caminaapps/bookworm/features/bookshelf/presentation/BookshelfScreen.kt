package com.caminaapps.bookworm.features.bookshelf.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
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
import com.caminaapps.bookworm.R
import com.caminaapps.bookworm.core.model.Book
import com.caminaapps.bookworm.core.model.BookshelfSortOrder
import com.caminaapps.bookworm.features.bookshelf.presentation.components.AddBookFloatingActionButton
import com.caminaapps.bookworm.features.bookshelf.presentation.components.BookList
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import timber.log.Timber

@OptIn(ExperimentalMaterialApi::class)
@ExperimentalComposeUiApi
@ExperimentalPermissionsApi
@Composable
fun BookshelfScreen(
    onScanBarcode: () -> Unit,
    onSearchOnline: () -> Unit,
    onBookClick: (id: Book) -> Unit,
    viewModel: BookshelfViewModel = hiltViewModel()
) {
    var sortingMenuExpanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.TopEnd)
            .padding(top = 24.dp, end = 8.dp)
    ) {
        SortingDropDownMenu(
            expanded = sortingMenuExpanded,
            onDismissRequest = { sortingMenuExpanded = false },
            onSelectedItem = { sortingMenuExpanded = false }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "") },
                actions = {
                    IconButton(onClick = { sortingMenuExpanded = !sortingMenuExpanded }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_sort_48),
                            contentDescription = null,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            AddBookFloatingActionButton(
                onManual = { /*TODO*/ },
                onSearch = onSearchOnline,
                onScan = onScanBarcode
            )
        }
    ) { innerPadding ->

        if (viewModel.uiState.books.isNotEmpty()) {
            Timber.d("books count: ${viewModel.uiState.books.count()}")
            BookList(
                modifier = Modifier.padding(innerPadding),
                books = viewModel.uiState.books,
                onItemClick = onBookClick,
            )
        } else {
            Text("add some books to get started")
        }
    }
}

@Composable
fun SortingDropDownMenu(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    onSelectedItem: (Int) -> Unit, // add preselected Item
    modifier: Modifier = Modifier,
) {
    val items = BookshelfSortOrder.values().map { stringResource(id = it.toStringResId()) }.toList()
    val (selectedOptionIndex, onOptionSelectedIndex) = remember { mutableStateOf(0) }

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismissRequest,
        modifier = modifier
    ) {
        Column(Modifier.selectableGroup()) {
            items.forEachIndexed { index, text ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .selectable(
                            selected = text == items[selectedOptionIndex],
                            onClick = { onOptionSelectedIndex(index) },
                            role = Role.RadioButton
                        )
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = text == items[selectedOptionIndex],
                        onClick = null // null recommended for accessibility with screenreaders
                    )
                    Text(
                        text = text,
                        style = MaterialTheme.typography.body1.merge(),
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
            }
        }
    }
}

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
