package com.caminaapps.bookworm.presentation.screens.wishlist

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun WishlistScreen(
    viewModel: WishlistViewModel = hiltViewModel()
) {
    Text("- Wishlist -")
}