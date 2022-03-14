package com.caminaapps.bookworm.presentation.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.caminaapps.bookworm.R

sealed class BottomNavigationScreen(
    val route: String,
    @StringRes val titleResourceId: Int,
    val icon: ImageVector
) {
    object Bookshelf : BottomNavigationScreen("bookshelf", R.string.screen_title_bookshelf, Icons.Rounded.Book)
    object Wishlist : BottomNavigationScreen("wishlist", R.string.screen_title_wishlist, Icons.Rounded.ShoppingBasket)
    object Settings : BottomNavigationScreen("settings", R.string.screen_title_settings, Icons.Rounded.Settings)
}


sealed class Screen(
    val route: String,
    val argumentKey: String
) {
    object Book : Screen("book/{bookId}", "bookId")
}
