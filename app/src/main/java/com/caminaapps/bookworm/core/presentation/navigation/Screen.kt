package com.caminaapps.bookworm.core.presentation.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.caminaapps.bookworm.R

sealed class BottomNavigationScreen(
    val route: String,
    @StringRes val titleResourceId: Int,
    @DrawableRes val imageResourceId: Int,
) {
    object Bookshelf :
        BottomNavigationScreen(
            route = "bookshelf",
            titleResourceId = R.string.screen_title_bookshelf,
            imageResourceId = R.drawable.ic_baseline_book_24
        )

    object Wishlist : BottomNavigationScreen(
        route = "wishlist",
        titleResourceId = R.string.screen_title_wishlist,
        imageResourceId = R.drawable.ic_baseline_shopping_basket_24
    )

    object Settings :
        BottomNavigationScreen(
            route = "settings",
            titleResourceId = R.string.screen_title_settings,
            imageResourceId = R.drawable.ic_baseline_settings_24
        )
}


sealed class Screen(
    val route: String,
    val argumentKey: String?
) {
    object BookDetail : Screen("book/{bookId}", "bookId") {
        fun createRoute(bookId: String) = "book/$bookId"
    }

    object SearchIsbnBookResult : Screen(route = "searchBookResult/{isbn}", argumentKey = "isbn") {
        fun createRoute(isbn: String) = "searchBookResult/$isbn"
    }

    object Camera : Screen("camera", argumentKey = null) {
        fun createRoute() = route
    }
}
