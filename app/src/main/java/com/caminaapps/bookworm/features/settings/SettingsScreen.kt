package com.caminaapps.bookworm.features.settings

import android.annotation.SuppressLint
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.caminaapps.bookworm.R
import com.caminaapps.bookworm.core.ui.component.TrackedScreen

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SettingsScreen(
    onLicenseInfoClick: () -> Unit,
//    viewModel: SettingsViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            TopAppBar {
                Text(text = "My Settings")
            }
        }
    ) {
        TrackedScreen(name = "Settings")
        Button(onClick = onLicenseInfoClick) {
            Text(text = stringResource(id = R.string.screen_title_license))
        }
    }
}
