package com.tellme.feature_questions.model

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.tellme.core.BaseAction
import com.tellme.core.BaseViewModel
import com.tellme.data_questions.domain.entity.Question
import com.tellme.feature_questions.QuestionFeatureNavScreen
import com.tellme.feature_questions.usecase.GetQuestionByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

data class QuestionDetailScreenState(
    val isLoading: Boolean,
    val isError: Boolean,
    val question: Question?
)

sealed class QuestionDetailScreenAction : BaseAction()

sealed class QuestionDetailScreenEvent {
    object GetQuestionEvent : QuestionDetailScreenEvent()
}

@HiltViewModel
class QuestionDetailScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getQuestionByIdUseCase: GetQuestionByIdUseCase
) : BaseViewModel<QuestionDetailScreenState, QuestionDetailScreenAction, QuestionDetailScreenEvent>(
    initialState = QuestionDetailScreenState(isLoading = false, isError = false, question = null)
) {

    private val questionId: String? =
        savedStateHandle[QuestionFeatureNavScreen.QuestionDetails.QUESTION_ID]

    init {
        obtainEvent(QuestionDetailScreenEvent.GetQuestionEvent)
    }

    override fun obtainEvent(viewEvent: QuestionDetailScreenEvent) {
        when (viewEvent) {
            is QuestionDetailScreenEvent.GetQuestionEvent -> getQuestion()
        }
    }

    private fun getQuestion() {
        viewModelScope.launch(Dispatchers.IO) {
            getQuestionByIdUseCase(questionId).collect { result ->
                withContext(Dispatchers.Main) {
                    when {
                        result.isLoading() -> viewState =
                            viewState.copy(isLoading = true, isError = false)

                        result.isError() -> viewState =
                            viewState.copy(isLoading = false, isError = true)

                        result.isSuccess() -> viewState = viewState.copy(
                            isLoading = false,
                            isError = false,
                            question = result.data
                        )
                    }
                }
            }
        }
    }
}