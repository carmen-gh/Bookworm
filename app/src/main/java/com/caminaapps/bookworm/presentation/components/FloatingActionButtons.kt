package com.caminaapps.bookworm.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Camera
import androidx.compose.material.icons.outlined.Edit
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
import com.caminaapps.bookworm.R
import com.caminaapps.bookworm.presentation.theme.BookwormTheme


@Suppress("unused")
@Composable
fun ColumnScope.SpeedDialItem(
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    FloatingActionButton(
        modifier = Modifier.size(SpeedDialFloatingActionButtonSize),
        onClick = onClick,
        content = content
    )
}

@Composable
fun SpeedDialFloatingActionButton(
    onClick: () -> Unit = {},
    icon: @Composable () -> Unit,
    content: @Composable ColumnScope.() -> Unit
) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            SpeedDialFloatingActionButtonSpace,
            alignment = Alignment.Bottom
        )
    ) {
        Box(
            modifier = Modifier.height(SpeedDialFloatingActionButtonBoxMaxHeight),
            contentAlignment = Alignment.BottomCenter,
        ) {
            this@Column.AnimatedVisibility(
                visible = expanded,
                enter = slideInVertically(initialOffsetY = { 200 }),
                exit = slideOutVertically(targetOffsetY = { 300 })
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(SpeedDialFloatingActionButtonSpace)) {
                    content()
                }
            }
        }
        FloatingActionButton(onClick = {
            expanded = !expanded
            onClick()
        }, content = icon)
    }
}

private val SpeedDialFloatingActionButtonBoxMaxHeight =
    40.dp * 6 + 24.dp * 5 // max amount of buttons should be 6
private val SpeedDialFloatingActionButtonSize = 40.dp
private val SpeedDialFloatingActionButtonSpace =   24.dp

enum class SpeedDialFloatingActionButtonValue {
    Closed,
    Expanded
}


@Preview
@Composable
fun SpeedDialFloatingActionButtonPreview() {

    BookwormTheme {
        Scaffold(
            floatingActionButtonPosition = FabPosition.End,
            floatingActionButton = {
                SpeedDialFloatingActionButton(
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = stringResource(id = R.string.button_add)
                        )
                    }
                ) {
                    SpeedDialItem(onClick = {}) {
                        Icon(
                            imageVector = Icons.Outlined.Edit,
                            contentDescription = stringResource(id = R.string.button_edit)
                        )
                    }
                    SpeedDialItem(onClick = {}) {
                        Icon(
                            imageVector = Icons.Outlined.Camera,
                            contentDescription = stringResource(id = R.string.button_edit)
                        )
                    }
                }
            }
        ) { }


    }
}