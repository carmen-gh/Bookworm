package com.caminaapps.bookworm.core.ui.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.caminaapps.bookworm.R
import com.caminaapps.bookworm.core.ui.theme.BookwormTheme


@Composable
fun TopAppBarNavigationUp(
    title: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    actions: @Composable RowScope.() -> Unit = {}
) {
    TopAppBar(
        modifier = modifier,
        title = { Text(title, maxLines = 2, overflow = TextOverflow.Ellipsis) },
        navigationIcon = {
            IconButton(onClick = onClick) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(id = R.string.button_back)
                )
            }
        },
        actions = actions
    )
}

@Composable
fun TopAppBarSlotNavigationUp(
    modifier: Modifier = Modifier,
    title: @Composable () -> Unit,
    onClick: () -> Unit,
    actions: @Composable RowScope.() -> Unit = {}
) {
    TopAppBar(
        modifier = modifier,
        title = title,
        navigationIcon = {
            IconButton(onClick = onClick) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(id = R.string.button_back)
                )
            }
        },
        actions = actions
    )
}

@Composable
fun TopAppBarNavigationClose(
    modifier: Modifier = Modifier,
    title: String,
    onClick: () -> Unit,
    actions: @Composable RowScope.() -> Unit = {}
) {
    TopAppBar(
        modifier = modifier,
        title = { Text(title, maxLines = 2, overflow = TextOverflow.Ellipsis) },
        navigationIcon = {
            IconButton(onClick = onClick) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = stringResource(id = R.string.button_close)
                )
            }
        },
        actions = actions
    )
}


@Preview("default")
@Preview("dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview("large font", fontScale = 2f)
@Composable
fun DefaultPreviewDark() {
    BookwormTheme() {
        Column {
            TopAppBarNavigationUp(
                title = "TopAppBar dasdfasdfTitleTopAppBar TitleTopAppBar TitleTopAppBar TitleTopAppBar TitleTopAppBar TitleTopAppBar TitleTopAppBar TitleTopAppBar TitleTopAppBar Title",
                onClick = {}
            )
            Spacer(modifier = Modifier.height(8.dp))
            TopAppBarNavigationClose(
                title = "TopAppBar Title",
                onClick = {},
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    }
}
