package com.tellme.core_ui.theme

import androidx.compose.ui.graphics.Color

val colorWhite = Color(0xFFFFFFFF)
val colorTeal = Color(0xFF25C3DA)
val colorYellow = Color(0xFFF7C74A)
val colorPurple = Color(0xFF5750A0)
val colorRed = Color(0xFFD53F46)


class AppColors(
    val primary: Color,
    val secondary: Color,
    val background: Color
)

val lightPalette = AppColors(
    primary = colorTeal,
    secondary = colorPurple,
    background = colorWhite
)

val darkPalette = AppColors(
    primary = colorTeal,
    secondary = colorPurple,
    background = colorWhite
)