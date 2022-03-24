package com.caminaapps.bookworm.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.*
import androidx.compose.material.FloatingActionButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


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
    var expanded by remember { mutableStateOf(false) }

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
private val SpeedDialFloatingActionButtonSpace = 24.dp

enum class SpeedDialFloatingActionButtonValue {
    Closed,
    Expanded
}