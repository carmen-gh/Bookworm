package com.caminaapps.bookworm.core.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BookwormBottomNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    val bottomNavigationItems = listOf(
        BottomNavigationScreen.Bookshelf,
        BottomNavigationScreen.Wishlist,
        BottomNavigationScreen.Settings
    )

    BottomNavigation(modifier = modifier) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        bottomNavigationItems.forEach { screen ->

            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = screen.imageResourceId),
                        contentDescription = screen.route
                    )
                },
                modifier = Modifier.testTag(screen.route),
                label = { Text(stringResource(id = screen.titleResourceId)) },
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}
