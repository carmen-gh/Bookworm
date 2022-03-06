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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.caminaapps.bookworm.R
import com.caminaapps.bookworm.presentation.theme.BookwormTheme


@Composable
fun TopAppBarBackNavigation(title: String, navController: NavController) {
    TopAppBar(
        title = { Text(title) },
        navigationIcon = {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(id = R.string.top_app_bar_back)
                )
            }
        }
    )
}


@Preview(showBackground = true, name = "TopAppBar")
@Composable
fun DefaultPreview() {
    BookwormTheme() {
        TopAppBarBackNavigation(title = "Test", navController = rememberNavController())
    }
}

@Preview(showBackground = true, name = "ToppAppBar dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DefaultPreviewDark() {
    BookwormTheme() {
        TopAppBarBackNavigation(title = "Test", navController = rememberNavController())
    }
}