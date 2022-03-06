package com.caminaapps.bookworm.presentation.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.caminaapps.bookworm.R

sealed class BottomNavigationScreen(
    val route: String,
    @StringRes val resourceId: Int,
    val icon: ImageVector
) {
    object Bookshelf : BottomNavigationScreen("crew_screen", R.string.screen_title_bookshelf, Icons.Rounded.Book)
    object Wishlist : BottomNavigationScreen("rockets_screen", R.string.screen_title_wishlist, Icons.Rounded.ShoppingBasket)
    object Settings : BottomNavigationScreen("settings_screen", R.string.screen_title_settings, Icons.Rounded.Settings)
}