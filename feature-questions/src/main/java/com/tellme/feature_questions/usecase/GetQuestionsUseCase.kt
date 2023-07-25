package com.tellme.feature_questions.usecase

import com.tellme.data_questions.domain.QuestionsRepository
import javax.inject.Inject

class GetQuestionsUseCase @Inject constructor(
    private val questionsRepository: QuestionsRepository
) {
    suspend operator fun invoke() = questionsRepository.getQuestions()
}