package com.caminaapps.bookworm.features.settings.navigation

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.window.layout.DisplayFeature
import com.caminaapps.bookworm.features.settings.LicenseScreen

const val LICENSES_INFO_ROUTE = "license_info_route"

fun NavController.navigateToLicensesInfo(navOptions: NavOptions? = null) {
    this.navigate(LICENSES_INFO_ROUTE, navOptions)
}

fun NavGraphBuilder.licensesInfoDestination(
    displayFeatures: List<DisplayFeature>,
    windowSizeClass: WindowSizeClass,
    onUpNavigation: () -> Unit,
) {
    composable(route = LICENSES_INFO_ROUTE) {
        LicenseScreen(
            displayFeatures = displayFeatures,
            windowSizeClass = windowSizeClass,
            onUpNavigationClick = onUpNavigation,
        )
    }
}
