package com.tellme.core_ui.components.button

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tellme.core_ui.R
import com.tellme.core_ui.theme.AppTheme
import com.tellme.core_ui.util.appShadow

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    buttonText: String? = null,
    iconPainter: Painter? = null,
    buttonColor: Color = AppTheme.colors.button,
    onClick: () -> Unit = remember { {} }
) {
    Surface(
        modifier = modifier
            .border(1.dp, AppTheme.colors.onPrimary, AppTheme.shapes.large.copy(CornerSize(50)))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                enabled = enabled,
                onClick = onClick
            )
            .appShadow(
                offsetX = 5.dp,
                offsetY = 5.dp,
                shape = AppTheme.shapes.large.copy(CornerSize(50))
            ),
        shape = AppTheme.shapes.large.copy(CornerSize(50)),
        color = buttonColor
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 12.dp, horizontal = 20.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            buttonText?.let { Text(text = buttonText, style = AppTheme.typography.button1) }
            iconPainter?.let {
                Spacer(modifier = Modifier.requiredWidth(10.dp))
                Image(
                    painter = iconPainter,
                    contentDescription = "Icon $buttonText"
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PrimaryButtonPreview() {
    AppTheme {
        Box(
            modifier = Modifier.padding(40.dp)
        ) {
            PrimaryButton(
                modifier = Modifier.fillMaxWidth(),
                buttonText = "Button Text",
                iconPainter = painterResource(id = R.drawable.ic_fire_emoji)
            ) {}
        }
    }
}
