package com.caminaapps.bookworm.presentation.screens.book.add

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddBookViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {}
