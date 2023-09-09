package com.tellme.feature_questions.screen

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.tellme.core_ui.theme.AppTheme
import com.tellme.feature_questions.model.QuestionsScreenAction
import com.tellme.feature_questions.model.QuestionsScreenEvent
import com.tellme.feature_questions.model.QuestionsScreenViewModel
import com.tellme.feature_questions.screen.components.DailyQuestionsPager
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

    if (viewState.value.isLoading) {
        CircularProgressIndicator(color = AppTheme.colors.primary)
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

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
