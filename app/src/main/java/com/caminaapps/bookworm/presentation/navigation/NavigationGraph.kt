package com.caminaapps.bookworm.presentation.navigation

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.caminaapps.bookworm.presentation.screens.bookshelf.BookshelfScreen
import com.caminaapps.bookworm.presentation.screens.bookshelf.BookshelfViewModel

@Composable
fun BookwormNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(navController, startDestination = BottomNavigationScreen.Bookshelf.route, modifier) {

        // Bookshelf
        composable(BottomNavigationScreen.Bookshelf.route) {
            BookshelfScreen(
                viewModel = hiltViewModel<BookshelfViewModel>()
            )
        }

        // Wishlist
        composable(BottomNavigationScreen.Wishlist.route) {
            Text(text = "Wishlist")
        }

        // Settings
        composable(BottomNavigationScreen.Settings.route) {
            Text(text = "Settings")
        }
    }
}