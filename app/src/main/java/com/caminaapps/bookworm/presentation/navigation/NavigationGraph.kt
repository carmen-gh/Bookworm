package com.caminaapps.bookworm.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.caminaapps.bookworm.presentation.screens.book.details.BookScreen
import com.caminaapps.bookworm.presentation.screens.book.details.BookViewModel
import com.caminaapps.bookworm.presentation.screens.bookshelf.BookshelfScreen
import com.caminaapps.bookworm.presentation.screens.settings.SettingsScreen
import com.caminaapps.bookworm.presentation.screens.settings.SettingsViewModel
import com.caminaapps.bookworm.presentation.screens.wishlist.WishlistScreen
import com.caminaapps.bookworm.presentation.screens.wishlist.WishlistViewModel
import com.caminaapps.bookworm.searchBookOnline.presentation.result.BookResultScreen
import com.caminaapps.bookworm.searchBookOnline.presentation.result.BookResultViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi

@ExperimentalComposeUiApi
@ExperimentalPermissionsApi
@Composable
fun BookwormNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(navController, startDestination = BottomNavigationScreen.Bookshelf.route, modifier) {

        // Bookshelf -------------------------------------------------------------------------------

        composable(BottomNavigationScreen.Bookshelf.route) {
            BookshelfScreen(
                showISBNSearchResult = { isbn ->
                    navController.navigate(Screen.SearchIsbnBookResult.createRoute(isbn))
                }
            )
        }

        composable(Screen.Book.route) {
            BookScreen(viewModel = hiltViewModel<BookViewModel>()) {
                navController.navigateUp()
            }
        }

        composable(Screen.SearchIsbnBookResult.route) {
            BookResultScreen(viewModel = hiltViewModel<BookResultViewModel>()) {
                navController.navigateUp()
            }
        }

        // Wishlist --------------------------------------------------------------------------------

        composable(BottomNavigationScreen.Wishlist.route) {
            WishlistScreen(
                viewModel = hiltViewModel<WishlistViewModel>()
            )
        }

        // Settings --------------------------------------------------------------------------------

        composable(BottomNavigationScreen.Settings.route) {
            SettingsScreen(
                viewModel = hiltViewModel<SettingsViewModel>()
            )
        }
    }
}
