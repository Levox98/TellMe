package com.tellme.core_ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tellme.core_ui.theme.AppTheme

@Composable
fun AppMainHeader(
    modifier: Modifier = Modifier,
    mainText: String,
    secondaryText: String? = null
) {
    Surface(
        color = AppTheme.colors.transparent
    ) {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = mainText, style = AppTheme.typography.h1)

            secondaryText?.let {
                Spacer(modifier = Modifier.requiredWidth(12.dp))

                Text(text = secondaryText, style = AppTheme.typography.subtitleAlt)
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun AppMainHeaderPreview() {
    AppTheme {
        Box {
            AppMainHeader(mainText = "Hello", secondaryText = "Catherine!")
        }
    }
}