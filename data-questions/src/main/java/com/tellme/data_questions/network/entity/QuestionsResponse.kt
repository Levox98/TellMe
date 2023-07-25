package com.tellme.data_questions.network.entity

import com.tellme.core_network.entity.ApiResponse

data class QuestionsResponse(
    val results: List<QuestionApiEntity>
) : ApiResponse