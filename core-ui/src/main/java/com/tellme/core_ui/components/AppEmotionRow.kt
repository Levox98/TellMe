package com.tellme.core_ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tellme.core_ui.R
import com.tellme.core_ui.theme.AppTheme
import com.tellme.core_ui.util.appShadow
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun AppEmotionRow(
    modifier: Modifier = Modifier,
    emojiResourcesList: ImmutableList<Int> = persistentListOf(
        R.drawable.ic_emoji_frown_sweating,
        R.drawable.ic_emoji_frown,
        R.drawable.ic_emoji_neutral,
        R.drawable.ic_emoji_smile,
        R.drawable.ic_emoji_grinning_smiling_eyes
    ),
) {
    Surface(
        modifier = modifier
            .appShadow(
                offsetX = 4.dp, offsetY = 4.dp, shape = AppTheme.shapes.large.copy(
                    CornerSize(50)
                )
            ),
        shape = AppTheme.shapes.large.copy(CornerSize(50)),
        color = AppTheme.colors.surface
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 66.dp, vertical = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            repeat(emojiResourcesList.size) {
                Image(
                    painter = painterResource(emojiResourcesList[it]),
                    contentDescription = null
                )
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun AppEmotionRowPreview() {
    AppTheme {
        Box(modifier = Modifier.fillMaxSize().padding(20.dp)) {
            AppEmotionRow(modifier = Modifier.fillMaxWidth())
        }
    }
}