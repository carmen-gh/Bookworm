package com.caminaapps.bookworm.app

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.caminaapps.bookworm.core.navigation.BookwormNavHost
import com.caminaapps.bookworm.core.navigation.TopLevelDestination
import com.caminaapps.bookworm.core.ui.theme.BookwormTheme

@ExperimentalComposeUiApi
@Composable
fun BookwormApp(
    windowSizeClass: WindowSizeClass,
    appState: BookwormAppState = rememberBookwormAppState(
        windowSizeClass = windowSizeClass
    ),
) {
    BookwormTheme {
        Scaffold(
            modifier = Modifier.semantics {
                testTagsAsResourceId = true
            },
            bottomBar = {
                if (appState.shouldShowBottomBar) {
                    BookwormBottomBar(
                        destinations = appState.topLevelDestinations,
                        onNavigationToDestination = appState::navigateToTopLevelDestination,
                        currentDestination = appState.currentDestination,
                        modifier = Modifier.testTag("BookwormBottomBar")
                    )
                }
            }
        ) { innerPadding ->
            BookwormNavHost(
                appState = appState,
                modifier = Modifier.padding(innerPadding),
            )
        }
    }
}

@Composable
fun BookwormBottomBar(
    destinations: List<TopLevelDestination>,
    onNavigationToDestination: (TopLevelDestination) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier,
) {
    BottomNavigation(modifier = modifier) {
        destinations.forEach { destination ->
            val selected = currentDestination.isTopLevelDestinationInHierarchy(destination)

            BottomNavigationItem(
                selected = selected,
                onClick = { onNavigationToDestination(destination) },
                icon = {
                    Icon(
                        painter = painterResource(id = destination.iconId),
                        contentDescription = null
                    )
                },
                label = { Text(stringResource(id = destination.titleTextId)) }
            )
        }
    }
}

private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false
