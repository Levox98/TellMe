package com.tellme.data_questions.network

import android.util.Log
import com.tellme.core.Either
import com.tellme.core_network.BaseApi
import com.tellme.data_questions.network.entity.QuestionApiEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuestionsApi @Inject constructor(
    private val questionsApiService: QuestionsApiService
) : BaseApi() {

    suspend fun getQuestions(): Either<List<QuestionApiEntity>> =
        doRequest(
            tag = "getQuestions",
            request = {
                questionsApiService.getQuestions()
            },
            mapper = { response ->
                Log.d("APP_LOG", "${response?.results}")
                response?.results ?: listOf()
            }
        )
}