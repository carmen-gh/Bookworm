package com.caminaapps.bookworm.features.bookshelf.navigation

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.caminaapps.bookworm.core.model.Book
import com.caminaapps.bookworm.features.bookshelf.presentation.BookshelfScreen

const val BOOKSHELF_ROUTE = "bookshelf_route"

fun NavController.navigateToBookshelf(navOptions: NavOptions? = null) {
    this.navigate(BOOKSHELF_ROUTE, navOptions)
}

@OptIn(ExperimentalComposeUiApi::class)
fun NavGraphBuilder.bookshelfDestination(
    onScanBarcode: () -> Unit,
    onSearchOnline: () -> Unit,
    onEnterBook: () -> Unit,
    onBook: (id: Book) -> Unit, // todo change to id: bookId
    onSearchList: () -> Unit,
) {
    composable(route = BOOKSHELF_ROUTE) {
        BookshelfScreen(
            onScanBarcode = onScanBarcode,
            onSearchOnline = onSearchOnline,
            onEnterBook = onEnterBook,
            onBook = onBook,
            onSearchList = onSearchList
        )
    }
}
