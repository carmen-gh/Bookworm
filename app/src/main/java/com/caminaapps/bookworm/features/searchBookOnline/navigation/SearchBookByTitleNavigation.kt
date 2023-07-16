package com.caminaapps.bookworm.features.searchBookOnline.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.caminaapps.bookworm.features.searchBookOnline.presentation.searchTitle.SearchForBookTitleScreen

const val SEARCH_BOOK_BY_TITLE_ROUTE = "search_book_by_title_route"

fun NavController.navigateToSearchBookByTitle(navOptions: NavOptions? = null) {
    this.navigate(SEARCH_BOOK_BY_TITLE_ROUTE, navOptions)
}

fun NavGraphBuilder.searchBookByTitleDestination(
    onUpNavigation: () -> Unit,
) {
    composable(route = SEARCH_BOOK_BY_TITLE_ROUTE) {
        SearchForBookTitleScreen(onNavigateUp = onUpNavigation)
    }
}
