package com.caminaapps.bookworm.features.wishlist.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.caminaapps.bookworm.features.wishlist.WishlistScreen

const val WISHLIST_ROUTE = "wishlist_route"

fun NavController.navigateToWishlist(navOptions: NavOptions? = null) {
    this.navigate(WISHLIST_ROUTE, navOptions)
}

fun NavGraphBuilder.wishlistDestination() {
    composable(WISHLIST_ROUTE) {
        WishlistScreen()
    }
}
