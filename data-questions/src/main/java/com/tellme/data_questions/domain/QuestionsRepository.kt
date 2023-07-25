package com.tellme.data_questions.domain

import com.tellme.core.Either
import com.tellme.data_questions.domain.entity.Question
import kotlinx.coroutines.flow.Flow

interface QuestionsRepository {
    suspend fun getQuestions(): Flow<Either<List<Question>>>
}