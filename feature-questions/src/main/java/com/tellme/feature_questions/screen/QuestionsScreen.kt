package com.tellme.feature_questions.screen

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSizeIn
import androidx.compose.foundation.layout.requiredWidthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QuestionAnswer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.tellme.feature_questions.model.QuestionsScreenAction
import com.tellme.feature_questions.model.QuestionsScreenEvent
import com.tellme.feature_questions.model.QuestionsScreenViewModel
import kotlinx.coroutines.flow.onEach

@Composable
fun QuestionsScreen(
    vm: QuestionsScreenViewModel,
    goToDetailScreen: (questionId: String?) -> Unit
) {

    val viewState = vm.viewStates.collectAsState()
    val viewActions = vm.viewActions

    val questions = viewState.value.questions

    LaunchedEffect(viewActions) {
        viewActions.onEach { action ->
            when (action) {
                is QuestionsScreenAction.GoToQuestionDetail -> goToDetailScreen(action.questionId)
            }
        }.collect {
            Log.d("APP_NAV", "$it")
        }
    }

    if (viewState.value.isLoading) {
        CircularProgressIndicator()
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        itemsIndexed(items = questions) { index, question ->
            QuestionCard(
                modifier = Modifier
                    .padding(horizontal = 32.dp)
                    .fillMaxWidth(),
                questionText = question.text,
                onClick = remember {
                    {
                        vm.obtainEvent(QuestionsScreenEvent.GoToAnswerQuestionEvent(question.objectId))
                    }
                }
            )
        }
    }
}

@Composable
private fun QuestionCard(
    modifier: Modifier = Modifier,
    questionText: String = "",
    onClick: () -> Unit = {}
) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colors.surface
    ) {
        Row(
            modifier = Modifier
                .height(IntrinsicSize.Max),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Row() {
                Text(
                    modifier = Modifier
                        .padding(vertical = 16.dp, horizontal = 16.dp),
                    text = questionText,
                    color = MaterialTheme.colors.onSurface,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.requiredWidthIn(min = 16.dp))
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .requiredWidthIn(min = 128.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onClick
                    )
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    shape = MaterialTheme.shapes.medium,
                    color = MaterialTheme.colors.error
                ) {
                    Icon(
                        modifier = Modifier
                            .padding(16.dp)
                            .requiredSizeIn(maxHeight = 32.dp),
                        imageVector = Icons.Default.QuestionAnswer,
                        contentDescription = null
                    )
                }
            }
        }
    }
}
