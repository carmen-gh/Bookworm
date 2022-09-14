package com.caminaapps.bookworm.core.model

import androidx.annotation.Keep

@Keep
enum class BookshelfSortOrder {
    DATE_ADDED_ASC,
    DATE_ADDED_DESC,
    TITLE_ASC,
    TITLE_DESC,
    AUTHOR_ASC,
    AUTHOR_DESC;

    companion object {
        fun getDefault() = DATE_ADDED_DESC
        fun valuesAsList() = values().toList()
    }
}
