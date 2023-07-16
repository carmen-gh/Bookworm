package com.caminaapps.bookworm.features.bookshelf.presentation.components

import android.annotation.SuppressLint
import androidx.compose.material.FabPosition
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.caminaapps.bookworm.R
import com.caminaapps.bookworm.core.ui.component.SpeedDialFloatingActionButton
import com.caminaapps.bookworm.core.ui.component.SpeedDialItem
import com.caminaapps.bookworm.core.ui.theme.BookwormTheme

@Composable
fun AddBookFloatingActionButton(
    onManual: () -> Unit,
    onSearch: () -> Unit,
    onScan: () -> Unit
) {
    SpeedDialFloatingActionButton(
        icon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_add_24),
                contentDescription = stringResource(id = R.string.button_add)
            )
        }
    ) {
        SpeedDialItem(onClick = onManual) {
            Icon(
                painter = painterResource(id = R.drawable.ic_outline_edit_24),
                contentDescription = stringResource(id = R.string.button_edit)
            )
        }
        SpeedDialItem(onClick = onSearch) {
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_search_42),
                contentDescription = stringResource(id = R.string.button_edit)
            )
        }
        SpeedDialItem(onClick = onScan) {
            Icon(
                painter = painterResource(id = R.drawable.ic_outline_photo_camera_24),
                contentDescription = stringResource(id = R.string.button_scan)
            )
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview(showBackground = true, name = "add floating button")
@Composable
fun PreviewAddFloatingButton() {
    BookwormTheme {
        Scaffold(
            floatingActionButtonPosition = FabPosition.End,
            floatingActionButton = {
                AddBookFloatingActionButton(
                    onManual = { },
                    onSearch = { },
                    onScan = { }
                )
            }
        ) {
        }
    }
}
