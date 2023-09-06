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
val typoH1 = defaultTextStyle.copy(fontFamily = fontFamilyKrofftsman, fontSize = 32.sp)
val typoH2 = defaultTextStyle.copy(fontFamily = fontFamilyNunitoSans, fontSize = 24.sp, fontWeight = FontWeight.Bold)
val typoH3 = defaultTextStyle.copy(fontFamily = fontFamilyNunitoSans, fontSize = 20.sp, fontWeight = FontWeight.Bold)

val typoBody1 = defaultTextStyle.copy(fontFamily = fontFamilyNunitoSans, fontSize = 20.sp, fontWeight = FontWeight.Normal)
val typoBody2 = defaultTextStyle.copy(fontFamily = fontFamilyNunitoSans, fontSize = 16.sp, fontWeight = FontWeight.Normal)


val typoButton1 = defaultTextStyle.copy(fontFamily = fontFamilyNunitoSans, fontSize = 24.sp, fontWeight = FontWeight.Bold)

val typography = AppTypography(
    h0 = typoH0,
    h1 = typoH1,
    h2 = typoH2,
    h3 = typoH3,
    body1 = typoBody1,
    body2 = typoBody2,
    button1 = typoButton1
)

class AppTypography(
    val h0: TextStyle,
    val h1: TextStyle,
    val h2: TextStyle,
    val h3: TextStyle,
    val body1: TextStyle,
    val body2: TextStyle,
    val button1: TextStyle
)