package com.caminaapps.bookworm.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.navOptions
import com.caminaapps.bookworm.app.BookwormAppState
import com.caminaapps.bookworm.features.bookshelf.navigation.BOOKSHELF_ROUTE
import com.caminaapps.bookworm.features.bookshelf.navigation.bookDetailsDestination
import com.caminaapps.bookworm.features.bookshelf.navigation.bookshelfDestination
import com.caminaapps.bookworm.features.bookshelf.navigation.bookshelfSearchDestination
import com.caminaapps.bookworm.features.bookshelf.navigation.navigateToBookDetails
import com.caminaapps.bookworm.features.bookshelf.navigation.navigateToBookshelfSearch
import com.caminaapps.bookworm.features.enterBook.navigation.enterBookDestination
import com.caminaapps.bookworm.features.enterBook.navigation.navigateToEnterBook
import com.caminaapps.bookworm.features.searchBookOnline.navigation.BARCODE_SCANNER_ROUTE
import com.caminaapps.bookworm.features.searchBookOnline.navigation.SEARCH_BOOK_BY_ISBN_ROUTE
import com.caminaapps.bookworm.features.searchBookOnline.navigation.barcodeScannerDestination
import com.caminaapps.bookworm.features.searchBookOnline.navigation.navigateToBarcodeScanner
import com.caminaapps.bookworm.features.searchBookOnline.navigation.navigateToSearchBookByIsbn
import com.caminaapps.bookworm.features.searchBookOnline.navigation.navigateToSearchBookByTitle
import com.caminaapps.bookworm.features.searchBookOnline.navigation.searchBookByIsbnDestination
import com.caminaapps.bookworm.features.searchBookOnline.navigation.searchBookByTitleDestination
import com.caminaapps.bookworm.features.settings.navigation.licensesInfoDestination
import com.caminaapps.bookworm.features.settings.navigation.navigateToLicensesInfo
import com.caminaapps.bookworm.features.settings.navigation.settingsDestination
import com.caminaapps.bookworm.features.wishlist.navigation.wishlistDestination

@ExperimentalComposeUiApi
@Composable
fun BookwormNavHost(
    appState: BookwormAppState,
    modifier: Modifier = Modifier,
    startDestination: String = BOOKSHELF_ROUTE,
) {
    val navController = appState.navController

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        // Bookshelf -------------------------------------------------------------------------------
        bookshelfDestination(
            onScanBarcode = navController::navigateToBarcodeScanner,
            onSearchOnline = navController::navigateToSearchBookByTitle,
            onEnterBook = navController::navigateToEnterBook,
            onBook = { navController.navigateToBookDetails(it.id) },
            onSearchList = navController::navigateToBookshelfSearch
        )

        barcodeScannerDestination(
            onBarcodeDetection = { isbn ->
                navController.navigateToSearchBookByIsbn(
                    isbn = isbn,
                    navOptions {
                        popUpTo(BARCODE_SCANNER_ROUTE) { inclusive = true }
                    }
                )
            },
            onCancel = navController::navigateUp
        )

        searchBookByIsbnDestination(
            onCloseScreen = navController::navigateUp,
            onScanBarcode = {
                navController.navigateToBarcodeScanner(
                    navOptions {
                        popUpTo(SEARCH_BOOK_BY_ISBN_ROUTE) { inclusive = true }
                    }
                )
            }
        )

        searchBookByTitleDestination(
            onUpNavigation = navController::navigateUp
        )

        bookshelfSearchDestination(
            onUpNavigation = navController::navigateUp,
            onSearchResultSelection = { bookId ->
                navController.navigateToBookDetails(bookId)
            }
        )

        enterBookDestination(
            onUpNavigation = navController::navigateUp
        )

        bookDetailsDestination(
            onUpNavigation = navController::navigateUp
        )

        // Wishlist -----------------------------------------------------------------------------------
        wishlistDestination()

        // Settings --------------------------------------------------------------------------------
        settingsDestination(
            onLicenseInfo = navController::navigateToLicensesInfo
        )

        licensesInfoDestination(
            displayFeatures = appState.displayFeatures,
            windowSizeClass = appState.windowSizeClass,
            onUpNavigation = navController::navigateUp
        )
    }
}
