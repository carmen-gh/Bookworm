package com.caminaapps.bookworm.features.settings.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.caminaapps.bookworm.features.settings.SettingsScreen

const val SETTINGS_ROUTE = "settings_route"

fun NavController.navigateToSettings(navOptions: NavOptions? = null) {
    this.navigate(SETTINGS_ROUTE, navOptions)
}

fun NavGraphBuilder.settingsDestination(
    onLicenseInfo: () -> Unit,
) {
    composable(route = SETTINGS_ROUTE) {
        SettingsScreen(onLicenseInfoClick = onLicenseInfo)
    }
}
