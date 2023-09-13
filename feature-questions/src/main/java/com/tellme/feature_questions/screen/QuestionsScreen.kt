package com.tellme.feature_questions.screen

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.tellme.core_ui.components.AppMainHeader
import com.tellme.core_ui.components.AppSegmentHeader
import com.tellme.core_ui.components.graph.AppGraph
import com.tellme.core_ui.theme.AppTheme
import com.tellme.feature_questions.model.QuestionsScreenAction
import com.tellme.feature_questions.model.QuestionsScreenEvent
import com.tellme.feature_questions.model.QuestionsScreenViewModel
import com.tellme.feature_questions.screen.components.DailyQuestionsPager
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.onEach

@OptIn(ExperimentalFoundationApi::class)
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

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            item {
                AppMainHeader(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(top = 20.dp)
                        .statusBarsPadding(),
                    mainText = "Hello",
                    secondaryText = "Catherine!"
                )
            }

            item { Spacer(modifier = Modifier.requiredHeight(30.dp)) }

            item {
                AppSegmentHeader(
                    modifier = Modifier
                        .padding(horizontal = 16.dp),
                    headerText = "TELL ME..."
                )
            }

            item { Spacer(modifier = Modifier.requiredHeight(20.dp)) }

            item {

                if (viewState.value.isLoading) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CircularProgressIndicator(color = AppTheme.colors.primary)
                    }
                } else {
                    DailyQuestionsPager(
                        questions = questions,
                        onQuestionClick = remember {
                            { question ->
                                vm.obtainEvent(
                                    QuestionsScreenEvent.GoToAnswerQuestionEvent(questionId = question.objectId)
                                )
                            }
                        }
                    )
                }
            }

            item { Spacer(modifier = Modifier.requiredHeight(42.dp)) }

            item {
                AppSegmentHeader(
                    modifier = Modifier
                        .padding(horizontal = 16.dp),
                    headerText = "MOOD"
                )
            }

            item { Spacer(modifier = Modifier.requiredHeight(20.dp)) }

            item {
                AppGraph(
                    modifier = Modifier
                        .padding(horizontal = 36.dp)
                        .navigationBarsPadding(),
                    graphHeader = {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 10.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "11.09 — 17.09",
                                style = AppTheme.typography.body2,
                                color = AppTheme.colors.surface
                            )
                        }
                    },
                    filledDividerColor = AppTheme.colors.surface,
                    rows = persistentListOf(1, 2, 3, 4, 5),
                    columns = persistentListOf("mon", "tue", "wed", "thu", "fri", "sat", "sun"),
                    rowTitle = {
                        Text(
                            text = "$it",
                            style = AppTheme.typography.body2,
                            textAlign = TextAlign.Center
                        )
                    },
                    columnTitle = {
                        Box(modifier = Modifier, contentAlignment = Alignment.Center) {
                            Text(
                                text = it,
                                style = AppTheme.typography.body2,
                                color = AppTheme.colors.surface,
                                textAlign = TextAlign.Center
                            )
                        }
                    },
                    markedCells = persistentListOf(
                        "mon" to 3,
                        "tue" to 4,
                        "wed" to 2,
                        "thu" to 4,
                        "fri" to 5,
                        "sat" to 1,
                        "sun" to 3
                    ),
                    markedCellContent = {
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = AppTheme.colors.surface
                        ) {}
                    }
                )
            }
        }
    }
}
