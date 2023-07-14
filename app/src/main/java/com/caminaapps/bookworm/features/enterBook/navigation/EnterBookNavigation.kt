package com.caminaapps.bookworm.features.enterBook.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.caminaapps.bookworm.features.enterBook.EnterBookScreen

const val ENTER_BOOK_ROUTE = "enter_book_route"

fun NavController.navigateToEnterBook(navOptions: NavOptions? = null) {
    this.navigate(ENTER_BOOK_ROUTE, navOptions)
}

fun NavGraphBuilder.enterBookDestination(
    onUpNavigation: () -> Unit,
) {
    composable(route = ENTER_BOOK_ROUTE) {
        EnterBookScreen(onUpNavigationClick = onUpNavigation)
    }
}
