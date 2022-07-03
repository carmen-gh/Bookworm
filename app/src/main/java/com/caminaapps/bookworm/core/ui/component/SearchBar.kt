package com.caminaapps.bookworm.core.ui.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.caminaapps.bookworm.R
import com.caminaapps.bookworm.core.ui.theme.BookwormTheme

@Composable
fun SearchBar(
    query: TextFieldValue,
    onQueryChange: (TextFieldValue) -> Unit,
    searchFocused: Boolean,
    onSearchFocusChange: (Boolean) -> Unit,
    onClearQuery: () -> Unit,
    onSearchKeyboardAction: () -> Unit,
    searching: Boolean,
    modifier: Modifier = Modifier
) {
    Surface(
        color = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onPrimary,
        shape = MaterialTheme.shapes.small,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 24.dp, vertical = 8.dp)
    ) {
        Box(Modifier.fillMaxSize()) {
            if (query.text.isEmpty()) {
                SearchHint()
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentHeight()
            ) {
                if (searchFocused) {
                    IconButton(onClick = onClearQuery) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            tint = MaterialTheme.colors.primaryVariant,
                            contentDescription = stringResource(R.string.button_back)
                        )
                    }
                }
                BasicTextField(
                    value = query,
                    onValueChange = onQueryChange,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions(onSearch = { onSearchKeyboardAction() }),
                    modifier = Modifier
                        .weight(1f)
                        .onFocusChanged {
                            onSearchFocusChange(it.isFocused)
                        }

                )
                if (searching) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colors.onPrimary,
                        modifier = Modifier
                            .padding(horizontal = 6.dp)
                            .size(36.dp)
                    )
                } else {
                    Spacer(Modifier.width(IconSize)) // balance arrow icon
                }
            }
        }
    }
}

private val IconSize = 48.dp

@Composable
private fun SearchHint() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize()
    ) {
        Icon(
            imageVector = Icons.Outlined.Search,
            tint = MaterialTheme.colors.onPrimary,
            contentDescription = stringResource(R.string.button_search)
        )
        Spacer(Modifier.width(8.dp))
        Text(
            text = stringResource(R.string.button_search),
            color = MaterialTheme.colors.onPrimary
        )
    }
}

@Preview("default")
@Preview("dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview("large font", fontScale = 2f)
@Composable
private fun SearchBarPreview() {
    BookwormTheme {
        Surface {
            SearchBar(
                query = TextFieldValue(""),
                onQueryChange = { },
                searchFocused = false,
                onSearchFocusChange = { },
                onClearQuery = { },
                onSearchKeyboardAction = { },
                searching = false
            )
        }
    }
}
