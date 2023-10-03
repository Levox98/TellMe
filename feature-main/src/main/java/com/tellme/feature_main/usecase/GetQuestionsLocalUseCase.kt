package com.tellme.feature_main.usecase

import com.tellme.data_questions.domain.QuestionsRepository
import javax.inject.Inject

class GetQuestionsLocalUseCase @Inject constructor(
    private val repository: QuestionsRepository
) {
    suspend operator fun invoke() = repository.getQuestionsLocal()
}