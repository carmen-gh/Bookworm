package com.caminaapps.bookworm.features.searchBookOnline.presentation.searchTitle

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.caminaapps.bookworm.R
import com.caminaapps.bookworm.core.model.Book
import com.caminaapps.bookworm.core.ui.component.FullScreenLoading
import com.caminaapps.bookworm.core.ui.component.TopAppBarSlotNavigationUp
import com.caminaapps.bookworm.core.ui.theme.BookwormTheme
import com.caminaapps.bookworm.features.searchBookOnline.presentation.searchTitle.SearchForBookTitleUiState.*


@Composable
fun SearchForBookTitleScreen(
    modifier: Modifier = Modifier,
    onBookClick: (Book) -> Unit,
    onNavigateUp: () -> Unit,
    viewModel: SearchForBookTitleViewModel = hiltViewModel()
) {
    var query by remember { mutableStateOf("") }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBarSlotNavigationUp(
                title = {
                    SimpleSearchBar(
                        onValueChange = { query = it },
                        value = query,
                        onSearchKeyboardAction = { viewModel.search(query) }
                    )
                },
                onClick = {
                    onNavigateUp()
                }
            )
        }
    ) {
        val uiState: SearchForBookTitleUiState by viewModel.uiState.collectAsState()

        when (uiState) {
            is Empty -> Text(text = "start searching") // TODO
            is Loading -> FullScreenLoading()
            is NoResults -> {
                Text(text = "no results")
                // TODO show no results matching}
            }
            is Success -> {
                SearchResults(searchResults = (uiState as Success).books, onBookClick = {}, onAddClick = {})
//                Text(text = "results")
                // TODO show list of results
            }
            is Error -> {
                Text(text = "Error occured")
            }
        }
    }

}

@Composable
fun SimpleSearchBar(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    onSearchKeyboardAction: () -> Unit
) {
    // state show trailing icon derivedStateOf

    TextField(
        textStyle = LocalTextStyle.current,
        value = value,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = { onSearchKeyboardAction() }),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = MaterialTheme.colors.onPrimary
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.primary
        ),
        placeholder = {
            Text(
                text = stringResource(R.string.search_placeholder),
                style = LocalTextStyle.current,
                color = MaterialTheme.colors.onPrimary
            )

        },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
    )
}


@Preview
@Composable
fun SearchForBookTitleScreenPreview() {
    BookwormTheme {
        SearchForBookTitleScreen(onBookClick = {}, onNavigateUp = {})
    }
}
