package com.tellme.feature_questions.screen

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.tellme.feature_questions.model.QuestionDetailScreenViewModel

@Composable
fun QuestionDetailScreen(vm: QuestionDetailScreenViewModel, questionId: String?) {

    Text(text = questionId ?: "null")
}