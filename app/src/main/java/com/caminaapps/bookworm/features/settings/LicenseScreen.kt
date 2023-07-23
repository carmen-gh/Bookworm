package com.caminaapps.bookworm.features.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.window.layout.DisplayFeature
import com.caminaapps.bookworm.core.ui.component.ListDetail
import com.caminaapps.bookworm.core.ui.component.SelectionVisibilityState
import com.caminaapps.bookworm.core.ui.component.TopAppBarNavigationUp
import com.google.accompanist.adaptive.HorizontalTwoPaneStrategy

@Composable
fun LicenseScreen(
    displayFeatures: List<DisplayFeature>,
    windowSizeClass: WindowSizeClass,
    onUpNavigationClick: () -> Unit,
    viewModel: LicenseViewModel = hiltViewModel(),
) {
    // Query for the current window size class
    val widthSizeClass by rememberUpdatedState(windowSizeClass.widthSizeClass)

    // The index of the currently selected word, or `null` if none is selected
    var selectedLibIndex: Int? by rememberSaveable { mutableStateOf(null) }

    // True if the detail is currently open. This is the primary control for "navigation".
    var isDetailOpen by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBarNavigationUp(
                title = "lajlsjf",
                onClick = onUpNavigationClick,
            )
        }
    ) { innerPadding ->
        ListDetail(
            isDetailOpen = isDetailOpen,
            onIsDetailOpen = { isDetailOpen = it },
            showListAndDetail =
            when (widthSizeClass) {
                WindowWidthSizeClass.Compact, WindowWidthSizeClass.Medium -> false
                WindowWidthSizeClass.Expanded -> true
                else -> true
            },
            detailKey = selectedLibIndex,
            list = { isDetailVisible ->
                val currentSelectedLibIndex = selectedLibIndex
                ListContent(
                    libNames = viewModel.libraries.map(LibInfo::name),
                    selectionState =
                    if (isDetailVisible && currentSelectedLibIndex != null) {
                        SelectionVisibilityState.ShowSelection(currentSelectedLibIndex)
                    } else {
                        SelectionVisibilityState.NoSelection
                    },
                    onIndexClick = { index ->
                        selectedLibIndex = index
                        isDetailOpen = true
                    },
                    modifier =
                    if (isDetailVisible) {
                        Modifier.padding(end = 8.dp)
                    } else {
                        Modifier
                    }
                )
            },
            detail = { isListVisible ->
                val libInfo = selectedLibIndex?.let(viewModel.libraries::get)
                DetailContent(
                    libInfo = libInfo,
                    modifier =
                    if (isListVisible) {
                        Modifier.padding(start = 8.dp)
                    } else {
                        Modifier
                    }
                )
            },
            twoPaneStrategy =
            HorizontalTwoPaneStrategy(
                splitFraction = 1f / 3f,
            ),
            displayFeatures = displayFeatures,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ListContent(
    libNames: List<String>,
    selectionState: SelectionVisibilityState,
    onIndexClick: (index: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier =
        modifier.then(
            when (selectionState) {
                SelectionVisibilityState.NoSelection -> Modifier
                is SelectionVisibilityState.ShowSelection ->
                    Modifier.selectableGroup()
            }
        )
    ) {
        itemsIndexed(libNames) { index, name ->
            val interactionSource = remember { MutableInteractionSource() }

            val interactionModifier =
                when (selectionState) {
                    SelectionVisibilityState.NoSelection -> {
                        Modifier.clickable(
                            interactionSource = interactionSource,
                            indication = rememberRipple(),
                            onClick = { onIndexClick(index) }
                        )
                    }

                    is SelectionVisibilityState.ShowSelection -> {
                        Modifier.selectable(
                            selected = index == selectionState.selectedIndex,
                            interactionSource = interactionSource,
                            indication = rememberRipple(), // ripple not working ???
                            onClick = { onIndexClick(index) }
                        )
                    }
                }

            val listItemColor =
                when (selectionState) {
                    SelectionVisibilityState.NoSelection -> MaterialTheme.colors.background
                    is SelectionVisibilityState.ShowSelection ->
                        if (index == selectionState.selectedIndex) {
                            MaterialTheme.colors.primary // selected marker
                        } else {
                            MaterialTheme.colors.background
                        }
                }
            ListItem(
                text = { Text(text = name) },
                modifier = Modifier
                    .then(interactionModifier)
                    .background(listItemColor)
            )
            Divider()
        }
    }
}

/** The content for the detail pane. */
@Composable
private fun DetailContent(
    libInfo: LibInfo?,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(vertical = 16.dp)
    ) {
        if (libInfo != null) {
            Text(text = libInfo.name, style = MaterialTheme.typography.h4)
            Text(text = libInfo.license)
        } else {
            Text(text = "-")
        }
    }
}
