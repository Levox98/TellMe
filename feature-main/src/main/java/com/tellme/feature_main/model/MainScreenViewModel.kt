package com.tellme.feature_main.model

import androidx.lifecycle.viewModelScope
import com.tellme.core.BaseAction
import com.tellme.core.BaseViewModel
import com.tellme.data_questions.domain.entity.Question
import com.tellme.feature_main.usecase.GetQuestionsLocalUseCase
import com.tellme.feature_main.usecase.LoadQuestionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

data class MainScreenState(
    val isLoading: Boolean,
    val isError: Boolean,
    val questions: List<Question> = emptyList()
)

sealed class MainScreenAction : BaseAction() {
    class GoToQuestionDetail(val questionId: String) : MainScreenAction()
}

sealed class MainScreenEvent {
    object GetMainEvent : MainScreenEvent()
    class GoToAnswerQuestionEvent(val questionId: String) : MainScreenEvent()
}

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val loadQuestionsUseCase: LoadQuestionsUseCase,
    private val getQuestionsLocalUseCase: GetQuestionsLocalUseCase
) : BaseViewModel<MainScreenState, MainScreenAction, MainScreenEvent>(
    initialState = MainScreenState(isLoading = false, isError = false, questions = listOf())
) {

    val emojiList = listOf(
        com.tellme.core_ui.R.drawable.ic_emoji_frown_sweating,
        com.tellme.core_ui.R.drawable.ic_emoji_frown,
        com.tellme.core_ui.R.drawable.ic_emoji_neutral,
        com.tellme.core_ui.R.drawable.ic_emoji_smile,
        com.tellme.core_ui.R.drawable.ic_emoji_grinning_smiling_eyes
    )

    init {
        obtainEvent(MainScreenEvent.GetMainEvent)
    }

    override fun obtainEvent(viewEvent: MainScreenEvent) {
        when (viewEvent) {
            is MainScreenEvent.GetMainEvent -> getAllQuestions()
            is MainScreenEvent.GoToAnswerQuestionEvent -> {
                sendAction(MainScreenAction.GoToQuestionDetail(viewEvent.questionId))
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