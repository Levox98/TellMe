package com.tellme.core_ui.util

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.tellme.core_ui.theme.colorBlack

fun Modifier.appShadow(
    color: Color = colorBlack,
    offsetX: Dp = 0.dp,
    offsetY: Dp = 0.dp,
    cornerRadius: Float? = null
) = then(
    drawBehind {
        drawIntoCanvas { canvas ->
            val paint = Paint()
            paint.color = color

            val leftPixel = offsetX.toPx()
            val topPixel = offsetY.toPx()
            val rightPixel = size.width + topPixel
            val bottomPixel = size.height + leftPixel

            canvas.drawRoundRect(
                left = leftPixel,
                top = topPixel,
                right = rightPixel,
                bottom = bottomPixel,
                radiusX = cornerRadius ?: 0f,
                radiusY = cornerRadius ?: 0f,
                paint = paint
            )
        }
    }
)