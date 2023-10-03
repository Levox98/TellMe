package com.tellme.feature_questions.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.EditCalendar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.tellme.core_ui.R
import com.tellme.core_ui.components.AppEmotionRow
import com.tellme.core_ui.components.AppMultilineTextField
import com.tellme.core_ui.components.button.PrimaryButton
import com.tellme.core_ui.components.header.AppMainHeader
import com.tellme.core_ui.components.header.AppSegmentHeaderSmall
import com.tellme.core_ui.theme.AppTheme
import com.tellme.feature_questions.model.QuestionDetailScreenViewModel

@Composable
fun QuestionDetailScreen(vm: QuestionDetailScreenViewModel) {

    val viewState = vm.viewStates.collectAsState()

    val question = viewState.value.question

    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier
                .statusBarsPadding()
                .padding(horizontal = 16.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(top = 24.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                AppMainHeader(
                    modifier = Modifier.padding(end = 24.dp),
                    mainText = "05.05",
                    mainTextStyle = AppTheme.typography.subtitleAlt
                )
                Icon(
                    imageVector = Icons.Outlined.EditCalendar,
                    contentDescription = null
                )
            }

            Spacer(modifier = Modifier.requiredHeight(28.dp))

            AppSegmentHeaderSmall(
                modifier = Modifier,
                headerText = "How are you feeling today?"
            )

            Spacer(modifier = Modifier.requiredHeight(16.dp))

            AppEmotionRow(modifier = Modifier.fillMaxWidth())

            Spacer(modifier = Modifier.requiredHeight(32.dp))

            AppSegmentHeaderSmall(
                modifier = Modifier,
                headerText = question?.text ?: "Question?"
            )

            Spacer(modifier = Modifier.requiredHeight(28.dp))

            AppMultilineTextField(
                modifier = Modifier.fillMaxSize().navigationBarsPadding(),
                value = vm.answerValue,
                onValueChange = vm::onAnswerChange,
                onFocusChanged = remember {{}},
                sendButton = {
                    PrimaryButton(
                        modifier = Modifier.fillMaxWidth(),
                        buttonText = "Send",
                        iconPainter = painterResource(id = R.drawable.ic_fire_emoji)
                    )
                }
            )
        }
    }
}