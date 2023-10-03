package com.tellme.core_ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

val LocalAppColors = staticCompositionLocalOf<AppColors> {
    error("No colors provided")
}

val LocalAppShapes = staticCompositionLocalOf<AppShapes> {
    error("No shapes provided")
}

val LocalAppTypography = staticCompositionLocalOf<AppTypography> {
    error("No typography provided")
}

@Composable
fun AppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {

    CompositionLocalProvider(
        LocalAppColors provides if (darkTheme) darkPalette else lightPalette,
        LocalAppShapes provides shapes,
        LocalAppTypography provides typography,
        content = content
    )
}

object AppTheme {
    val colors: AppColors
        @Composable
        get() = LocalAppColors.current
    val shapes: AppShapes
        @Composable
        get() = LocalAppShapes.current
    val typography: AppTypography
        @Composable
        get() = LocalAppTypography.current
}