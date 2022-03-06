package com.caminaapps.bookworm.presentation.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.caminaapps.bookworm.presentation.navigation.BookwormBottomNavigation
import com.caminaapps.bookworm.presentation.navigation.BookwormNavHost
import com.caminaapps.bookworm.presentation.theme.BookwormTheme


@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    BookwormTheme {
        val navController = rememberNavController()

        Scaffold(
            modifier = modifier,
            bottomBar = { BookwormBottomNavigation(navController = navController) }
        ){ innerPadding ->
            BookwormNavHost(
                navController = navController,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}




@Preview(showBackground = true)
@Composable
fun DefaultPreviewMainScreen() {
    MainScreen()
}