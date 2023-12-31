package com.tellme.data_questions.domain

import com.tellme.core.Either
import com.tellme.data_questions.domain.entity.Question
import kotlinx.coroutines.flow.Flow

interface QuestionsRepository {
    suspend fun loadQuestions(): Flow<Either<List<Question>>>
    suspend fun getQuestionsLocal(): List<Question>
    suspend fun getQuestionById(questionId: String?): Flow<Either<Question?>>
}