package com.caminaapps.bookworm.features.settings

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel()
) {
//    Text("- Settings -")
    Button(onClick = { throw RuntimeException("Test crash") }) {
        Text(text = "crash")
    }
}
