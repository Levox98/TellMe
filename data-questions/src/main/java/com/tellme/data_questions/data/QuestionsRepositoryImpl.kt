package com.tellme.data_questions.data

import com.tellme.core.AppError
import com.tellme.core.Either
import com.tellme.data_questions.data.db.QuestionDao
import com.tellme.data_questions.data.mappers.toData
import com.tellme.data_questions.data.mappers.toDomain
import com.tellme.data_questions.domain.QuestionsRepository
import com.tellme.data_questions.domain.entity.Question
import com.tellme.data_questions.network.QuestionsApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class QuestionsRepositoryImpl @Inject constructor(
    private val questionsApi: QuestionsApi,
    private val questionDao: QuestionDao
) : QuestionsRepository {
    override suspend fun loadQuestions(): Flow<Either<List<Question>>> = flow {
        emit(Either.loading())

        val result = questionsApi.getQuestions()

        result.onError { emit(Either.error(result.error ?: AppError.Unknown())) }

        result.onSuccess { questionList ->
            questionList?.toData()?.let {
                questionDao.insertQuestions(it)
            }

            val listToEmit = questionDao.getQuestions().toDomain()

            emit(Either.success(listToEmit))
        }
    }

    override suspend fun getQuestionsLocal(): List<Question> = questionDao.getQuestions().toDomain()

    override suspend fun getQuestionById(questionId: String?): Flow<Either<Question?>> = flow {
        emit(Either.loading())

        if (questionId == null) emit(Either.error(AppError.Unknown("Question ID is null")))

        val result = questionDao.getQuestionById(questionId!!)

        emit(Either.success(result?.toDomain()))
    }
}