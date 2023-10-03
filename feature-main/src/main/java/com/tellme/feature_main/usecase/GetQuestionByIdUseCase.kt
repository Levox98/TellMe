package com.tellme.feature_main.usecase

import com.tellme.data_questions.domain.QuestionsRepository
import javax.inject.Inject

class GetQuestionByIdUseCase @Inject constructor(
    private val questionsRepository: QuestionsRepository
) {
    suspend operator fun invoke(questionId: String?) = questionsRepository.getQuestionById(questionId)
}