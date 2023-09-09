package com.tellme.feature_questions.model

import androidx.lifecycle.viewModelScope
import com.tellme.core.BaseAction
import com.tellme.core.BaseViewModel
import com.tellme.data_questions.domain.entity.Question
import com.tellme.feature_questions.usecase.GetQuestionsLocalUseCase
import com.tellme.feature_questions.usecase.LoadQuestionsUseCase
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

sealed class QuestionsScreenAction : BaseAction() {
    class GoToQuestionDetail(val questionId: String) : QuestionsScreenAction()
}

sealed class QuestionsScreenEvent {
    object GetQuestionsEvent : QuestionsScreenEvent()
    class GoToAnswerQuestionEvent(val questionId: String) : QuestionsScreenEvent()
}

@HiltViewModel
class QuestionsScreenViewModel @Inject constructor(
    private val loadQuestionsUseCase: LoadQuestionsUseCase,
    private val getQuestionsLocalUseCase: GetQuestionsLocalUseCase
) : BaseViewModel<QuestionsScreenState, QuestionsScreenAction, QuestionsScreenEvent>(
    initialState = QuestionsScreenState(isLoading = false, isError = false, questions = listOf())
) {
    init {
        obtainEvent(QuestionsScreenEvent.GetQuestionsEvent)
    }

    override fun obtainEvent(viewEvent: QuestionsScreenEvent) {
        when (viewEvent) {
            is QuestionsScreenEvent.GetQuestionsEvent -> getAllQuestions()
            is QuestionsScreenEvent.GoToAnswerQuestionEvent -> {
                sendAction(QuestionsScreenAction.GoToQuestionDetail(viewEvent.questionId))
            }
        }
    }

    private fun getAllQuestions() {
        viewModelScope.launch(Dispatchers.IO) {
            loadQuestionsUseCase().collect { state ->
                withContext(Dispatchers.Main) {
                    when {
                        state.isError() -> {
                            viewState = if (getQuestionsLocal().isNotEmpty()) {
                                viewState.copy(isLoading = false, questions = getQuestionsLocal())
                            } else {
                                viewState.copy(isError = true, isLoading = false)
                            }
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

    private suspend fun getQuestionsLocal(): List<Question> = getQuestionsLocalUseCase()
}