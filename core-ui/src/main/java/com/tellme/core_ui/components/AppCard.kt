package com.tellme.core_ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tellme.core_ui.R
import com.tellme.core_ui.theme.AppTheme
import com.tellme.core_ui.util.appShadow

@Composable
fun AppCard(
    modifier: Modifier = Modifier,
    surfaceColor: Color? = null,
    content: @Composable () -> Unit
) {
    val density = LocalDensity.current.density

    Surface(
        modifier = modifier
            .requiredWidth(240.dp)
            .appShadow(offsetX = 4.dp, offsetY = 4.dp, cornerRadius = 40 * density),
        color = surfaceColor ?: AppTheme.colors.primary,
        shape = AppTheme.shapes.large
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 32.dp)
        ) {
            content()
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AppCardPreview() {
    AppTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            AppCard(
                modifier = Modifier.align(Alignment.TopCenter),
                surfaceColor = null
            ) {
                Column(
                    modifier = Modifier
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Today", style = AppTheme.typography.subtitle1)

                        Image(
                            painter = painterResource(id = R.drawable.ic_fire_emoji),
                            contentDescription = null
                        )
                    }

                    Spacer(modifier = Modifier.requiredHeight(24.dp))

                    Text(text = "How are you today? ", minLines = 4, maxLines = 4, overflow = TextOverflow.Ellipsis)
                }
            }
        }
    }
}
