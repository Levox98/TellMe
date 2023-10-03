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
    fontFamily = fontFamilyKrofftsman,
    fontStyle = FontStyle.Normal,
    color = colorBlack
)

val h1normal = defaultTextStyle.copy(
    fontWeight = FontWeight.Normal,
    fontSize = 42.sp,
    lineHeight = 39.sp
)

val h2normal = defaultTextStyle.copy(
    fontWeight = FontWeight.Normal,
    fontSize = 32.sp,
    lineHeight = 24.sp
)

val body1normal = defaultTextStyle.copy(
    fontFamily = fontFamilyNunitoSans,
    fontWeight = FontWeight.Normal,
    fontSize = 20.sp,
    lineHeight = 24.sp
)

val body2normal = defaultTextStyle.copy(
    fontFamily = fontFamilyNunitoSans,
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp,
    lineHeight = 18.sp
)

val subtitle1bold = defaultTextStyle.copy(
    fontFamily = fontFamilyNunitoSans,
    fontWeight = FontWeight.Bold,
    fontSize = 20.sp,
    lineHeight = 24.sp
)

val subtitle2bold = defaultTextStyle.copy(
    fontFamily = fontFamilyNunitoSans,
    fontWeight = FontWeight.Bold,
    fontSize = 16.sp,
    lineHeight = 18.sp
)

val subtitleAltNormal = defaultTextStyle.copy(
    fontFamily = fontFamilyNunitoSans,
    fontWeight = FontWeight.Normal,
    fontSize = 32.sp,
    lineHeight = 36.sp
)

val button1bold = defaultTextStyle.copy(
    fontFamily = fontFamilyNunitoSans,
    fontWeight = FontWeight.Bold,
    fontSize = 24.sp,
    lineHeight = 24.sp
)

val typography = AppTypography(
    h1 = h1normal,
    h2 = h2normal,
    body1 = body1normal,
    body2 = body2normal,
    subtitle1 = subtitle1bold,
    subtitle2 = subtitle2bold,
    subtitleAlt = subtitleAltNormal,
    button1 = button1bold
)

class AppTypography(
    val h1: TextStyle,
    val h2: TextStyle,
    val body1: TextStyle,
    val body2: TextStyle,
    val subtitle1: TextStyle,
    val subtitle2: TextStyle,
    val subtitleAlt: TextStyle,
    val button1: TextStyle
)