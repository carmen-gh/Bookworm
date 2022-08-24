package com.caminaapps.bookworm.features.wishlist

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.caminaapps.bookworm.core.ui.component.TrackedScreen

@Composable
fun WishlistScreen(
    viewModel: WishlistViewModel = hiltViewModel()
) {
    TrackedScreen(name = "Wishlist")
    Text("- Wishlist -")
}
