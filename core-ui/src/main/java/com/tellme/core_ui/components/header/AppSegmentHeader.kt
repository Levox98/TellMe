package com.tellme.core_ui.components.header

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.tellme.core_ui.theme.AppTheme

@Composable
fun AppSegmentHeader(
    modifier: Modifier = Modifier,
    headerText: String
) {
    Surface(
        color = AppTheme.colors.transparent
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = headerText,
                style = AppTheme.typography.h2,
                textAlign = TextAlign.Start
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun AppSegmentHeaderPreview() {
    AppTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            AppSegmentHeader(headerText = "TELL ME...")
        }
    }
}