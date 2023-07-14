package com.caminaapps.bookworm.features.searchBookOnline.navigation

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.caminaapps.bookworm.features.searchBookOnline.presentation.searchBarcode.BookBarcodeResultScreen

const val SEARCH_BOOK_BY_ISBN_ROUTE = "search_book_by_isbn_route"
private const val ISBN_ARG = "isbn"

class SearchBookByIsbnArgs(val isbn: String) {
    constructor(savedStateHandle: SavedStateHandle) :
        this(Uri.decode(checkNotNull(savedStateHandle[ISBN_ARG])))
}

fun NavController.navigateToSearchBookByIsbn(isbn: String, navOptions: NavOptions) {
    val encodedIsbn = Uri.encode(isbn)
    this.navigate("$SEARCH_BOOK_BY_ISBN_ROUTE/$encodedIsbn", navOptions)
}

fun NavGraphBuilder.searchBookByIsbnDestination(
    onCloseScreen: () -> Unit,
    onScanBarcode: () -> Unit,
) {
    composable(
        route = "$SEARCH_BOOK_BY_ISBN_ROUTE/{$ISBN_ARG}",
        arguments = listOf(
            navArgument(ISBN_ARG) { type = NavType.StringType }
        )
    ) {
        BookBarcodeResultScreen(onCloseScreen = onCloseScreen, onScanBarcode = onScanBarcode)
    }
}
