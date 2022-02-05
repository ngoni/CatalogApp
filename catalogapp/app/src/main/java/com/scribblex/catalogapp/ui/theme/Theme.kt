package com.scribblex.catalogapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun CatalogAppTheme(
    darkMode: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = if (darkMode) DarkThemeColors else LightThemeColors,
        content = content,
        typography = Typography,
        shapes = Shapes
    )
}