package com.caminaapps.bookworm.features.searchBookOnline.presentation.searchTitle

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.caminaapps.bookworm.R
import com.caminaapps.bookworm.core.ui.component.FullScreenLoading
import com.caminaapps.bookworm.core.ui.component.TopAppBarSlotNavigationUp
import com.caminaapps.bookworm.core.ui.theme.BookwormTheme
import com.caminaapps.bookworm.features.searchBookOnline.presentation.searchTitle.SearchForBookTitleUiState.Empty
import com.caminaapps.bookworm.features.searchBookOnline.presentation.searchTitle.SearchForBookTitleUiState.Error
import com.caminaapps.bookworm.features.searchBookOnline.presentation.searchTitle.SearchForBookTitleUiState.Loading
import com.caminaapps.bookworm.features.searchBookOnline.presentation.searchTitle.SearchForBookTitleUiState.NoResults
import com.caminaapps.bookworm.features.searchBookOnline.presentation.searchTitle.SearchForBookTitleUiState.Success

@Composable
fun SearchForBookTitleScreen(
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SearchForBookTitleViewModel = hiltViewModel()
) {
    var query by remember { mutableStateOf("") }

    Scaffold(modifier = modifier, topBar = {
        TopAppBarSlotNavigationUp(title = {
            SimpleSearchBar(modifier = Modifier.padding(vertical = 4.dp),
                onValueChange = {
                    query = it
                    viewModel.onQueryChanged()
                },
                value = query,
                onSearchKeyboardAction = { viewModel.search(query) },
                onResetValue = {
                    query = ""
                    viewModel.onQueryChanged()
                })
        }, onClick = {
            onNavigateUp()
        })
    }) { innerPadding ->
        val uiState: SearchForBookTitleUiState by viewModel.uiState.collectAsState()

        when (uiState) {
            is Empty -> OpenLibraryView(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxWidth()
            )
            is Loading -> FullScreenLoading()
            is NoResults -> NoResults(query)
            is Success -> {
                SearchResults(modifier = Modifier.padding(innerPadding),
                    searchResults = (uiState as Success).books,
                    onResultClick = {
                        viewModel.onAddBook(it)
                        onNavigateUp()
                    })
            }
            is Error -> Text(text = stringResource((uiState as Error).message))
        }
    }
}

@Composable
fun OpenLibraryView(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .wrapContentSize()
            .padding(top = 80.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(R.string.search_engine_text),
            style = MaterialTheme.typography.subtitle1,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Image(
            painterResource(R.drawable.openlibrary_logo),
            contentDescription = "open library",
            modifier = Modifier.size(width = 200.dp, height = 60.dp)
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SimpleSearchBar(
    value: String,
    onValueChange: (String) -> Unit,
    onSearchKeyboardAction: () -> Unit,
    onResetValue: () -> Unit,
    modifier: Modifier = Modifier,
) {
    // state show trailing icon derivedStateOf
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(value = value,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = {
            onSearchKeyboardAction()
            keyboardController?.hide()
        }),
        trailingIcon = {
            Icon(imageVector = Icons.Default.Close,
                contentDescription = null,
                tint = MaterialTheme.colors.primary,
                modifier = Modifier.clickable { onResetValue() })
        },
        placeholder = {
            Text(
                text = stringResource(R.string.search_placeholder),
                color = MaterialTheme.colors.primary
            )

        },
        textStyle = MaterialTheme.typography.subtitle1,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent
        ),
        maxLines = 1,
        modifier = modifier.fillMaxWidth()
    )
}

@Preview
@Composable
fun SearchForBookTitleScreenPreview() {
    BookwormTheme {
        SearchForBookTitleScreen(onNavigateUp = {})
    }
}
