package com.tellme.feature_questions.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.tellme.feature_questions.model.QuestionsScreenViewModel

@Composable
fun QuestionsScreen(vm: QuestionsScreenViewModel) {

    val viewState = vm.viewStates.collectAsState()
    
    val questions = viewState.value.questions

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        itemsIndexed(items = questions) { index, question ->
            Text(text = question.text)
        }
    }
}