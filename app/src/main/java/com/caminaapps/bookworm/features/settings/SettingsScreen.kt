package com.caminaapps.bookworm.features.settings

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.caminaapps.bookworm.core.ui.component.TrackedScreen

@Composable
fun SettingsScreen(
//    viewModel: SettingsViewModel = hiltViewModel()
) {
    TrackedScreen(name = "Settings")
    Text("- Settings -")
}
