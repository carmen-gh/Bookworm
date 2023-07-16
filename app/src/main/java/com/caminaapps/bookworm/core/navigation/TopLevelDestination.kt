package com.caminaapps.bookworm.core.navigation

import com.caminaapps.bookworm.R

/**
 * Type for the top level destinations in the application. Each of these destinations
 * can contain one or more screens (based on the window size). Navigation from one screen to the
 * next within a single destination will be handled directly in composables.
 */
enum class TopLevelDestination(
    val iconId: Int,
    val iconTextId: Int,
    val titleTextId: Int,
) {
    BOOKSHELF(
        iconId = R.drawable.ic_baseline_book_24,
        iconTextId = R.string.screen_title_bookshelf,
        titleTextId = R.string.screen_title_bookshelf
    ),
    WISHLIST(
        iconId = R.drawable.ic_baseline_shopping_basket_24,
        iconTextId = R.string.screen_title_wishlist,
        titleTextId = R.string.screen_title_wishlist
    ),
    SETTINGS(
        iconId = R.drawable.ic_baseline_settings_24,
        iconTextId = R.string.screen_title_settings,
        titleTextId = R.string.screen_title_settings
    )
}
