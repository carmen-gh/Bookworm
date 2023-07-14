package com.caminaapps.bookworm.features.bookshelf.navigation

import android.net.Uri
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.caminaapps.bookworm.core.model.BookId
import com.caminaapps.bookworm.features.bookshelf.presentation.BookDetailsScreen

@VisibleForTesting
private const val BOOK_ID_ARG = "bookId"
private const val BOOK_DETAILS_ROUTE = "book_details_route"

class BookDetailsArgs(val bookId: BookId) {
    constructor(savedStateHandle: SavedStateHandle) :
        this(Uri.decode(checkNotNull(savedStateHandle[BOOK_ID_ARG])))
}

fun NavController.navigateToBookDetails(bookId: BookId) {
    val encodedId = Uri.encode(bookId)
    this.navigate("$BOOK_DETAILS_ROUTE/$encodedId") {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.bookDetailsDestination(
    onUpNavigation: () -> Unit,
) {
    composable(
        route = "$BOOK_DETAILS_ROUTE/{$BOOK_ID_ARG}",
        arguments = listOf(
            navArgument(BOOK_ID_ARG) { type = NavType.StringType },
        ),
    ) {
        BookDetailsScreen(onUpNavigationClick = onUpNavigation)
    }
}
