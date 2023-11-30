package com.tellme.data_questions.network.entity

data class QuestionsRequest(
    val start: Int,
    val end: Int
) {
    fun asJson(): Nothing = TODO("Not yet implemented")
}

//{"dayOfYear":{"$gte":326,"$lte":329}}