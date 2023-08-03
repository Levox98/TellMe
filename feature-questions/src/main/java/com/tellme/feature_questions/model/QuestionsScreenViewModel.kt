package com.tellme.feature_questions.model

import androidx.lifecycle.viewModelScope
import com.tellme.core.BaseAction
import com.tellme.core.BaseViewModel
import com.tellme.data_questions.domain.entity.Question
import com.tellme.feature_questions.usecase.GetQuestionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

data class QuestionsScreenState(
    val isLoading: Boolean,
    val isError: Boolean,
    val questions: List<Question> = emptyList()
)

sealed class QuestionsScreenAction : BaseAction()

sealed class QuestionsScreenEvent {
    object GetQuestionsEvent : QuestionsScreenEvent()
}

@HiltViewModel
class QuestionsScreenViewModel @Inject constructor(
    private val getQuestionsUseCase: GetQuestionsUseCase
) : BaseViewModel<QuestionsScreenState, QuestionsScreenAction, QuestionsScreenEvent>(
    initialState = QuestionsScreenState(isLoading = false, isError = false, questions = listOf())
) {
    init {
        obtainEvent(QuestionsScreenEvent.GetQuestionsEvent)
    }

    override fun obtainEvent(viewEvent: QuestionsScreenEvent) {
        when (viewEvent) {
            is QuestionsScreenEvent.GetQuestionsEvent -> getAllQuestions()
        }
    }

    private fun getAllQuestions() {
        viewModelScope.launch(Dispatchers.IO) {
            getQuestionsUseCase().collect { state ->
                withContext(Dispatchers.Main) {
                    when {
                        state.isError() -> {
                            viewState = viewState.copy(isError = true, isLoading = false)
                        }
                        state.isLoading() -> {
                            viewState = viewState.copy(isLoading = true, isError = false)
                        }
                        state.isSuccess() -> {
                            viewState = viewState.copy(questions = state.data ?: emptyList(), isLoading = false)
                        }
                    }
                }
            }
        }
    }
}