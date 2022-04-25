package com.caminaapps.bookworm.core.presentation.theme

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color


val md_theme_light_primary = Color(0xFFFB8C00)
val md_theme_light_onPrimary = Color(0xFFffffff)
val md_theme_light_secondary = Color(0xFF00ACC1)
val md_theme_light_onSecondary = Color(0xFFffffff)
val md_theme_light_error = Color(0xFFB3261E)
val md_theme_light_onError = Color(0xFFFFFFFF)
val md_theme_light_background = Color(0xFFFFFBFE)
val md_theme_light_onBackground = Color(0xFF1C1B1F)
val md_theme_light_surface = Color(0xFFFFFBFE)
val md_theme_light_onSurface = Color(0xFF1C1B1F)

val md_theme_dark_primary = Color(0xFF6ad2ff)
val md_theme_dark_onPrimary = Color(0xFF003548)
val md_theme_dark_secondary = Color(0xFF7CDB44)
val md_theme_dark_onSecondary = Color(0xFF163800)
val md_theme_dark_error = Color(0xFFF07069)
val md_theme_dark_onError = Color(0xFF601410)
val md_theme_dark_background = Color(0xFF1C1B1F)
val md_theme_dark_onBackground = Color(0xFFE6E1E5)
val md_theme_dark_surface = Color(0xFF1C1B1F)
val md_theme_dark_onSurface = Color(0xFFE6E1E5)


internal val LightColorPalette = lightColors(
    primary = md_theme_light_primary,
    onPrimary = md_theme_light_onPrimary,
    secondary = md_theme_light_secondary,
    onSecondary = md_theme_light_onSecondary,
    error = md_theme_light_error,
    onError = md_theme_light_onError,
    background = md_theme_light_background,
    onBackground = md_theme_light_onBackground,
    surface = md_theme_light_surface,
    onSurface = md_theme_light_onSurface,
)

internal val DarkColorPalette = darkColors(
    primary = md_theme_dark_primary,
    onPrimary = md_theme_dark_onPrimary,
    secondary = md_theme_dark_secondary,
    onSecondary = md_theme_dark_onSecondary,
    error = md_theme_dark_error,
    onError = md_theme_dark_onError,
    background = md_theme_dark_background,
    onBackground = md_theme_dark_onBackground,
    surface = md_theme_dark_surface,
    onSurface = md_theme_dark_onSurface,
)
