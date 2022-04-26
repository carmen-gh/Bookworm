package com.caminaapps.bookworm.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.caminaapps.bookworm.core.presentation.screens.book.details.BookScreen
import com.caminaapps.bookworm.core.presentation.screens.settings.SettingsScreen
import com.caminaapps.bookworm.core.presentation.screens.wishlist.WishlistScreen
import com.caminaapps.bookworm.features.bookshelf.presentation.BookshelfScreen
import com.caminaapps.bookworm.features.searchBookOnline.presentation.barcodeScanner.CameraScreen
import com.caminaapps.bookworm.features.searchBookOnline.presentation.result.BookResultScreen
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
                onScanBarcode = {
                    navController.navigate(Screen.Camera.createRoute())
                }
            )
        }

        composable(Screen.Book.route) {
            BookScreen {
                navController.navigateUp()
            }
        }

        composable(Screen.SearchIsbnBookResult.route) {
            BookResultScreen(
                onCloseScreenClick = { navController.navigateUp() },
                onScanClick = {
                    navController.navigate(Screen.Camera.createRoute()) {
                        popUpTo(Screen.SearchIsbnBookResult.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.Camera.route) {
            CameraScreen(
                onClose = { navController.navigateUp() },
                onBarcodeDetected = { isbn ->
                    navController.navigate(Screen.SearchIsbnBookResult.createRoute(isbn)) {
                        popUpTo(Screen.Camera.route) { inclusive = true }
                    }
                }
            )
        }

        // Wishlist --------------------------------------------------------------------------------
        composable(BottomNavigationScreen.Wishlist.route) {
            WishlistScreen()
        }

        // Settings --------------------------------------------------------------------------------
        composable(BottomNavigationScreen.Settings.route) {
            SettingsScreen()
        }
    }
}
