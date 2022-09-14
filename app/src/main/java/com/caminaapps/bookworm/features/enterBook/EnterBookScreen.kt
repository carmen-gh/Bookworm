package com.caminaapps.bookworm.features.enterBook

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.caminaapps.bookworm.R
import com.caminaapps.bookworm.core.ui.component.TrackedScreen
import com.caminaapps.bookworm.core.ui.theme.BookwormTheme

@Composable
fun EnterBookScreen(
    viewModel: EnterBookViewModel = hiltViewModel(),
    onUpNavigationClick: () -> Unit,
) {
    TrackedScreen(name = "Enter book")

    EnterBookContent(onSave = {})
}

@Composable
fun EnterBookContent(
    onSave: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var title by rememberSaveable { mutableStateOf("") }
    var subtitle by rememberSaveable { mutableStateOf("") }
    var author by rememberSaveable { mutableStateOf("") }
    var published by rememberSaveable { mutableStateOf("") }
    var isFavourite by rememberSaveable { mutableStateOf(false) }
    var isFinished by rememberSaveable { mutableStateOf(false) }

    ConstraintLayout(modifier = modifier) {

        val (inputColumn, checkboxColumn) = createRefs()

        Column(
            modifier = Modifier.constrainAs(inputColumn) {
                top.linkTo(parent.top, margin = 16.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                value = title,
                label = { Text(text = stringResource(R.string.book_title)) },
                onValueChange = { title = it }
            )
            OutlinedTextField(
                value = subtitle,
                label = { Text(text = stringResource(R.string.book_subtitle)) },
                onValueChange = { subtitle = it }
            )
            OutlinedTextField(
                value = author,
                label = { Text(text = stringResource(R.string.book_author)) },
                onValueChange = { author = it }
            )
            OutlinedTextField(
                value = published,
                label = { Text(text = stringResource(R.string.book_published)) },
                onValueChange = { published = it }
            )
        }
        Column(
            modifier = Modifier
                .padding(start = 16.dp)
                .constrainAs(checkboxColumn) {
                    top.linkTo(inputColumn.bottom, margin = 16.dp)
                    start.linkTo(inputColumn.start)
                    end.linkTo(inputColumn.end)
                    width = Dimension.fillToConstraints
                },
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = stringResource(R.string.book_favourite),
                    modifier = Modifier.weight(1.0F)
                )
                Switch(checked = isFavourite, onCheckedChange = { isFavourite = it })
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = stringResource(R.string.book_finished),
                    modifier = Modifier.weight(1.0F)
                )
                Switch(checked = isFinished, onCheckedChange = { isFinished = it })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddBookScreenPreview() {
    BookwormTheme {
        EnterBookContent(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        )
    }
}
