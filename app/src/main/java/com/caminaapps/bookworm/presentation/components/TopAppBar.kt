package com.caminaapps.bookworm.presentation.components

import android.content.res.Configuration
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.caminaapps.bookworm.R
import com.caminaapps.bookworm.presentation.theme.BookwormTheme


@Composable
fun TopAppBarNavigationUp(title: String, onClick: () -> Unit) {
    TopAppBar(
        title = { Text(title) },
        navigationIcon = {
            IconButton(onClick = onClick) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(id = R.string.button_back)
                )
            }
        }
    )
}


@Preview(showBackground = true, name = "TopAppBar")
@Composable
fun DefaultPreview() {
    BookwormTheme() {
        TopAppBarNavigationUp(title = "TopAppBar Title", onClick = {})
    }
}

@Preview(showBackground = true, name = "TopAppBar dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DefaultPreviewDark() {
    BookwormTheme() {
        TopAppBarNavigationUp(title = "TopAppBar Title", onClick = {})
    }
}