package com.caminaapps.bookworm.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.caminaapps.bookworm.features.bookshelf.presentation.BookDetailsScreen
import com.caminaapps.bookworm.features.bookshelf.presentation.BookshelfScreen
import com.caminaapps.bookworm.features.searchBookOnline.presentation.searchBarcode.BookBarcodeResultScreen
import com.caminaapps.bookworm.features.searchBookOnline.presentation.searchBarcode.CameraScreen
import com.caminaapps.bookworm.features.searchBookOnline.presentation.searchTitle.SearchForBookTitleScreen
import com.caminaapps.bookworm.features.settings.SettingsScreen
import com.caminaapps.bookworm.features.wishlist.WishlistScreen
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
                onBookClick = { book ->
                    navController.navigate(Screen.BookDetail.createRoute(bookId = book.id))
                },
                onScanBarcode = {
                    navController.navigate(Screen.Camera.createRoute())
                },
                onSearchOnline = {
                    navController.navigate(Screen.SearchBookByTitle.createRoute())
                }
            )
        }

        composable(Screen.BookDetail.route) {
            BookDetailsScreen {
                navController.navigateUp()
            }
        }

        composable(Screen.SearchIsbnBookResult.route) {
            BookBarcodeResultScreen(
                onCloseScreen = { navController.navigateUp() },
                onScanBarcode = {
                    navController.navigate(Screen.Camera.createRoute()) {
                        popUpTo(Screen.SearchIsbnBookResult.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.Camera.route) {
            CameraScreen(
                onClose = { navController.navigateUp() },
                onBarcodeDetection = { isbn ->
                    navController.navigate(Screen.SearchIsbnBookResult.createRoute(isbn)) {
                        popUpTo(Screen.Camera.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.SearchBookByTitle.route) {
            SearchForBookTitleScreen(onNavigateUp = { navController.navigateUp() })
        }

        // Wishlist -----------------------------------------------------------------------------------
        composable(BottomNavigationScreen.Wishlist.route) {
            WishlistScreen()
        }

        // Settings --------------------------------------------------------------------------------
        composable(BottomNavigationScreen.Settings.route) {
            SettingsScreen()
        }
    }
}
