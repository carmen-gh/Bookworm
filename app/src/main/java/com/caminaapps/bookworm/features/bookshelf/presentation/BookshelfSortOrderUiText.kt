package com.caminaapps.bookworm.features.bookshelf.presentation

import androidx.annotation.StringRes
import com.caminaapps.bookworm.R
import com.caminaapps.bookworm.core.model.BookshelfSortOrder

@StringRes
fun BookshelfSortOrder.toStringResId(): Int {
    return when (this) {
        BookshelfSortOrder.DATE_ADDED_ASC -> R.string.book_sort_order_date_asc
        BookshelfSortOrder.DATE_ADDED_DESC -> R.string.book_sort_order_date_desc
        BookshelfSortOrder.TITLE_ASC -> R.string.book_sort_order_title_asc
        BookshelfSortOrder.TITLE_DESC -> R.string.book_sort_order_title_desc
        BookshelfSortOrder.AUTHOR_ASC -> R.string.book_sort_order_author_asc
        BookshelfSortOrder.AUTHOR_DESC -> R.string.book_sort_order_author_desc
    }
}
