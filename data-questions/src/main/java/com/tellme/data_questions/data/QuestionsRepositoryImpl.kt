package com.tellme.data_questions.data

import com.tellme.core.AppError
import com.tellme.core.Either
import com.tellme.data_questions.data.entity.toDomain
import com.tellme.data_questions.domain.QuestionsRepository
import com.tellme.data_questions.domain.entity.Question
import com.tellme.data_questions.network.QuestionsApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class QuestionsRepositoryImpl @Inject constructor(
    private val questionsApi: QuestionsApi
) : QuestionsRepository {
    override suspend fun getQuestions(): Flow<Either<List<Question>>> = flow {
        emit(Either.loading())

        val result = questionsApi.getQuestions()

        result.onError { emit(Either.error(result.error ?: AppError.Unknown())) }

        result.onSuccess { emit(Either.success(result.data?.toDomain())) }
    }
}