package com.caminaapps.bookworm.features.enterBook

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EnterBookViewModel @Inject constructor(
    private val saveBook: SaveEnteredBookUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel()
