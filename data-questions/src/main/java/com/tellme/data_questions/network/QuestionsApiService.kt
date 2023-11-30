package com.tellme.data_questions.network

import com.tellme.core.AppConfig
import com.tellme.core_network.entity.ApiRequest
import com.tellme.data_questions.network.entity.QuestionsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface QuestionsApiService {

    @Headers(
        "X-Parse-Application-Id: ${AppConfig.Api.PARSE_APP_ID}",
        "X-Parse-REST-API-Key: ${AppConfig.Api.PARSE_API_KEY}"
    )
    @GET("/classes/Question")
    suspend fun getQuestions(): Response<QuestionsResponse>

    @Headers(
        "X-Parse-Application-Id: ${AppConfig.Api.PARSE_APP_ID}",
        "X-Parse-REST-API-Key: ${AppConfig.Api.PARSE_API_KEY}"
    )
    @GET("/classes/Question")
    suspend fun getQuestionsWithParams(
        @Query("where")
        condition: ApiRequest
    ): Response<QuestionsResponse>
}