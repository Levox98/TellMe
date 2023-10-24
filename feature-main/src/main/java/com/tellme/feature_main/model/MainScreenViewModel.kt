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
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

data class MainScreenState(
    val isLoading: Boolean,
    val isError: Boolean,
    val questions: List<Question> = emptyList(),
    val initialPagerIndex: Int = 0
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
    initialState = MainScreenState(
        isLoading = false,
        isError = false,
        questions = listOf(),
        initialPagerIndex = 0
    )
) {

    val emojiList = listOf(
        com.tellme.core_ui.R.drawable.ic_emoji_frown_sweating,
        com.tellme.core_ui.R.drawable.ic_emoji_frown,
        com.tellme.core_ui.R.drawable.ic_emoji_neutral,
        com.tellme.core_ui.R.drawable.ic_emoji_smile,
        com.tellme.core_ui.R.drawable.ic_emoji_grinning_smiling_eyes
    )

    private val currentDate = Calendar.getInstance(Locale.getDefault())

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

                            val index = state.data?.indexOfFirst { question ->
                                val questionDate = Calendar.getInstance(Locale.getDefault())
                                questionDate.time = question.assignedDate

                                "${currentDate.get(Calendar.DAY_OF_MONTH)}.${currentDate.get(Calendar.MONTH)}.${currentDate.get(Calendar.YEAR)}" ==
                                        "${questionDate.get(Calendar.DAY_OF_MONTH)}.${questionDate.get(Calendar.MONTH)}.${questionDate.get(Calendar.YEAR)}"
                            }

                            viewState = viewState.copy(
                                questions = state.data ?: emptyList(),
                                isLoading = false,
                                initialPagerIndex = index ?: 0
                            )
                        }
                    }
                }
            }
        }
    }

    private suspend fun getQuestionsLocal(): List<Question> = getQuestionsLocalUseCase()
}