package com.tellme.core_ui.theme

import androidx.compose.ui.graphics.Color

val colorWhite = Color(0xFFFFFFFF)
val colorBlack = Color(0xFF000000)
val colorTeal = Color(0xFF25C3DA)
val colorYellow = Color(0xFFF7C74A)
val colorPurple = Color(0xFF5750A0)
val colorRed = Color(0xFFD53F46)

val colorTransparent = Color(0x00000000)


class AppColors(
    val primary: Color,
    val onPrimary: Color,
    val secondary: Color,
    val onSecondary: Color,
    val background: Color,
    val onBackground: Color,
    val surface: Color,
    val button: Color,
    val transparent: Color
)

val lightPalette = AppColors(
    primary = colorTeal,
    onPrimary = colorBlack,
    secondary = colorPurple,
    onSecondary = colorWhite,
    background = colorWhite,
    onBackground = colorBlack,
    surface = colorRed,
    button = colorYellow,
    transparent = colorTransparent
)

val darkPalette = AppColors(
    primary = colorTeal,
    onPrimary = colorBlack,
    secondary = colorPurple,
    onSecondary = colorWhite,
    background = colorWhite,
    onBackground = colorBlack,
    surface = colorRed,
    button = colorYellow,
    transparent = colorTransparent
)