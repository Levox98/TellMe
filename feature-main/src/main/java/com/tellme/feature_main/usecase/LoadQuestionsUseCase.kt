package com.tellme.feature_main.usecase

import com.tellme.data_questions.domain.QuestionsRepository
import javax.inject.Inject

class LoadQuestionsUseCase @Inject constructor(
    private val questionsRepository: QuestionsRepository
) {
    suspend operator fun invoke() = questionsRepository.loadQuestions()
}