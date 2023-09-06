package com.tellme.core_ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

class AppShapes(
    val small: Shape,
    val medium: Shape,
    val large: Shape
)

val shapes = AppShapes(
    small = RoundedCornerShape(10.dp),
    medium = RoundedCornerShape(20.dp),
    large = RoundedCornerShape(40.dp)
)
