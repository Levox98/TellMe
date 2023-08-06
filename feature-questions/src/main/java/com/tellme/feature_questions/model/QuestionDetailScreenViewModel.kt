package com.tellme.feature_questions.model

import com.tellme.core.BaseAction
import com.tellme.core.BaseViewModel
import com.tellme.data_questions.domain.entity.Question
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class QuestionDetailScreenState(
    val isLoading: Boolean,
    val isError: Boolean,
    val question: Question?
)

sealed class QuestionDetailScreenAction : BaseAction()

sealed class QuestionDetailScreenEvent {

}

@HiltViewModel
class QuestionDetailScreenViewModel @Inject constructor() : BaseViewModel<QuestionDetailScreenState, QuestionDetailScreenAction, QuestionDetailScreenEvent>(
    initialState = QuestionDetailScreenState(isLoading = false, isError = false, question = null)
) {
    override fun obtainEvent(viewEvent: QuestionDetailScreenEvent) {
        TODO("Not yet implemented")
    }
}