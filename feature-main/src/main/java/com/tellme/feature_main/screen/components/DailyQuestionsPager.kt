package com.tellme.feature_main.screen.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.tellme.core.toHumanString
import com.tellme.core_ui.R
import com.tellme.core_ui.components.AppCard
import com.tellme.core_ui.components.AppHorizontalPager
import com.tellme.core_ui.theme.AppTheme
import com.tellme.core_ui.util.calculateCurrentOffsetForPage
import com.tellme.data_questions.domain.entity.Question
import kotlin.math.abs


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DailyQuestionsPager(
    modifier: Modifier = Modifier,
    state: PagerState = rememberPagerState(),
    questions: List<Question>,
    onQuestionClick: (question: Question) -> Unit
) {
    AppHorizontalPager(
        items = questions,
        modifier = modifier,
        contentPadding = PaddingValues(start = 100.dp, end = 100.dp, bottom = 4.dp),
        state = state
    ) { index, question ->

        val pageOffset = abs(state.calculateCurrentOffsetForPage(index))
        val scale = lerp(1f, .9f, pageOffset)

        AppCard(
            modifier = Modifier
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                }
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    enabled = true,
                    onClick = { onQuestionClick(question) }
                ),
            surfaceColor = AppTheme.colors.primary.copy(blue = AppTheme.colors.primary.blue * scale)
        ) {
            Column(
                modifier = Modifier
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = question.assignedDate.toHumanString(), style = AppTheme.typography.subtitle1)

                    Image(
                        painter = painterResource(id = R.drawable.ic_fire_emoji),
                        contentDescription = null
                    )
                }

                Spacer(modifier = Modifier.requiredHeight(24.dp))

                Text(text = question.text, minLines = 4, maxLines = 4, overflow = TextOverflow.Ellipsis)
            }
        }
    }
}