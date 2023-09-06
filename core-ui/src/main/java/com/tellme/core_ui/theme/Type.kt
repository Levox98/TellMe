package com.tellme.core_ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.tellme.core_ui.R

val fontKrofftsman = Font(
    resId = R.font.kroftsmann
)

val fontNunitoSans = Font(
    resId = R.font.nunito_sans
)

val fontFamilyKrofftsman = FontFamily(fonts = listOf(fontKrofftsman))
val fontFamilyNunitoSans = FontFamily(fonts = listOf(fontNunitoSans))

val defaultTextStyle = TextStyle(
    fontFamily = FontFamily.Default,
    fontStyle = FontStyle.Normal,
    color = colorPurple
)

val typoH0 = defaultTextStyle.copy(fontFamily = fontFamilyKrofftsman, fontSize = 42.sp)
val typoBody1 = defaultTextStyle.copy(fontFamily = fontFamilyNunitoSans, fontSize = 24.sp, fontWeight = FontWeight.Normal)

val typography = AppTypography(
    h0 = typoH0,
    body1 = typoBody1
)

class AppTypography(
    val h0: TextStyle,
    val body1: TextStyle
)