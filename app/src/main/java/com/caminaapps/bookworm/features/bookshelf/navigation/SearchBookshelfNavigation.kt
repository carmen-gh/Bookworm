package com.caminaapps.bookworm.features.bookshelf.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.caminaapps.bookworm.core.model.BookId
import com.caminaapps.bookworm.features.bookshelf.presentation.BookshelfSearchScreen

const val BOOKSHELF_SEARCH_ROUTE = "bookshelf_search_route"

fun NavController.navigateToBookshelfSearch(navOptions: NavOptions? = null) {
    this.navigate(BOOKSHELF_SEARCH_ROUTE, navOptions)
}

fun NavGraphBuilder.bookshelfSearchDestination(
    onUpNavigation: () -> Unit,
    onSearchResultSelection: (BookId) -> Unit,
) {
    composable(route = BOOKSHELF_SEARCH_ROUTE) {
        BookshelfSearchScreen(
            onUpNavigationClick = onUpNavigation,
            onSearchResultSelection = onSearchResultSelection
        )
    }
}
