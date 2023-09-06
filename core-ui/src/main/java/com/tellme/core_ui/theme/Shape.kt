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
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(4.dp),
    large = RoundedCornerShape(8.dp)
)
