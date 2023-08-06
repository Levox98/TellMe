package com.tellme.feature_questions.screen

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.tellme.feature_questions.model.QuestionDetailScreenViewModel

@Composable
fun QuestionDetailScreen(vm: QuestionDetailScreenViewModel) {

    val viewState = vm.viewStates.collectAsState()

    val question = viewState.value.question

    if (viewState.value.isLoading) {
        CircularProgressIndicator()
    } else {
        Text(text = question?.text ?: "null")
    }
}