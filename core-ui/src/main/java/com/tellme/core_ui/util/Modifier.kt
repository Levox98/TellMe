package com.tellme.core_ui.util

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.tellme.core_ui.theme.colorBlack

fun Modifier.appShadow(
    color: Color = colorBlack,
    offsetX: Dp = 0.dp,
    offsetY: Dp = 0.dp,
    cornerRadius: Float? = null,
    drawBottomShadow: Boolean = true
) = then(
    drawBehind {
        drawIntoCanvas { canvas ->
            val paint = Paint()
            paint.color = color

            val topPixel = offsetY.toPx()
            val leftPixel = offsetX.toPx()
            val rightPixel = size.width + leftPixel
            val bottomPixel = size.height + topPixel

            canvas.drawRoundRect(
                left = leftPixel,
                top = topPixel,
                right = rightPixel,
                bottom = if (drawBottomShadow) bottomPixel else size.height,
                radiusX = cornerRadius ?: 0f,
                radiusY = cornerRadius ?: 0f,
                paint = paint
            )

            val path = Path()

            path.moveTo(size.width, 0f)
            path.lineTo(rightPixel, topPixel)
            path.lineTo(leftPixel, if (drawBottomShadow) bottomPixel else size.height)
            path.lineTo(0f, size.height)
            path.lineTo(size.width, 0f)
            path.fillType = PathFillType.NonZero


            if (cornerRadius == null || cornerRadius == 0f) {
                canvas.drawPath(
                    path,
                    paint = paint
                )
            }
        }
    }
)