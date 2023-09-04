package com.tellme.data_questions.network.entity

import java.util.Date

data class QuestionApiEntity(
    val createdAt: Date,
    val objectId: String,
    val questionText: String,
    val updatedAt: String
)