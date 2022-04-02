package com.caminaapps.bookworm.presentation.screens.book.add

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.caminaapps.bookworm.R
import com.caminaapps.bookworm.presentation.components.IconTextField
import com.caminaapps.bookworm.presentation.theme.BookwormTheme

@Composable
fun AddBookScreen(
    viewModel: AddBookViewModel
) {

}

@Composable
fun AddBookContentView(
    modifier: Modifier = Modifier,
) {
    var title by rememberSaveable { mutableStateOf("") }
    var author by rememberSaveable { mutableStateOf("") }
    var published by rememberSaveable { mutableStateOf("") }
    var subtitle by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        IconTextField(
            value = title,
            onValueChange = { title = it },
            label = R.string.textfield_label_title,
            imageVector = Icons.Filled.Book
        )
        IconTextField(
            value = subtitle,
            onValueChange = { subtitle = it },
            label = R.string.textfield_label_subtitle,
            imageVector = Icons.Filled.Book
        )
        IconTextField(
            value = author,
            onValueChange = { author = it },
            label = R.string.textfield_label_author,
            imageVector = Icons.Filled.Person
        )
        IconTextField(
            value = published,
            onValueChange = { published = it },
            label = R.string.textfield_label_published_date,
            imageVector = Icons.Filled.CalendarToday
        )

    }
}


@Preview(showBackground = true)
@Composable
fun AddBookScreenPreview() {
    BookwormTheme {
        AddBookContentView(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        )
    }
}