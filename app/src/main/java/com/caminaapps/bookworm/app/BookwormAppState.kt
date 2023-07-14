package com.caminaapps.bookworm.app

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.caminaapps.bookworm.core.navigation.TopLevelDestination
import com.caminaapps.bookworm.features.bookshelf.navigation.BOOKSHELF_ROUTE
import com.caminaapps.bookworm.features.bookshelf.navigation.navigateToBookshelf
import com.caminaapps.bookworm.features.enterBook.navigation.ENTER_BOOK_ROUTE
import com.caminaapps.bookworm.features.searchBookOnline.navigation.BARCODE_SCANNER_ROUTE
import com.caminaapps.bookworm.features.searchBookOnline.navigation.SEARCH_BOOK_BY_ISBN_ROUTE
import com.caminaapps.bookworm.features.searchBookOnline.navigation.SEARCH_BOOK_BY_TITLE_ROUTE
import com.caminaapps.bookworm.features.settings.navigation.SETTINGS_ROUTE
import com.caminaapps.bookworm.features.settings.navigation.navigateToSettings
import com.caminaapps.bookworm.features.wishlist.navigation.WISHLIST_ROUTE
import com.caminaapps.bookworm.features.wishlist.navigation.navigateToWishlist
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberBookwormAppState(
    windowSizeClass: WindowSizeClass,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
): BookwormAppState {
    return remember(
        navController,
        coroutineScope,
        windowSizeClass,
    ) {
        BookwormAppState(
            navController,
            coroutineScope,
            windowSizeClass,
        )
    }
}

@Stable
class BookwormAppState(
    val navController: NavHostController,
    val coroutineScope: CoroutineScope,
    val windowSizeClass: WindowSizeClass,
) {
    val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() = when (currentDestination?.route) {
            BOOKSHELF_ROUTE -> TopLevelDestination.BOOKSHELF
            WISHLIST_ROUTE -> TopLevelDestination.WISHLIST
            SETTINGS_ROUTE -> TopLevelDestination.SETTINGS
            else -> null
        }

    val shouldShowBottomBar: Boolean
        @Composable get() = when (currentDestination?.route) {
            SEARCH_BOOK_BY_TITLE_ROUTE -> false
            SEARCH_BOOK_BY_ISBN_ROUTE -> false
            ENTER_BOOK_ROUTE -> false
            BARCODE_SCANNER_ROUTE -> false
            else -> true
        }

    /**
     * List of top level destinations to be used in the TopBar, BottomBar and NavRail.
     */
    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.values().asList()

    /**
     * UI logic for navigating to a top level destination in the app. Top level destinations have
     * only one copy of the destination of the back stack, and save and restore state whenever you
     * navigate to and from it.
     *
     * @param topLevelDestination: The destination the app needs to navigate to.
     */
    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        val topLevelNavOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

        when (topLevelDestination) {
            TopLevelDestination.BOOKSHELF -> navController.navigateToBookshelf(topLevelNavOptions)
            TopLevelDestination.WISHLIST -> navController.navigateToWishlist(topLevelNavOptions)
            TopLevelDestination.SETTINGS -> navController.navigateToSettings(topLevelNavOptions)
        }
    }

//    fun navigateToBookshelf() {
//        navController.navigateToBookshelf()
//    }
}
