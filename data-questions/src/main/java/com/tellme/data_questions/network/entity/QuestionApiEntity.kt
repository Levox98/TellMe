package com.tellme.data_questions.network.entity

import java.util.Date

data class QuestionApiEntity(
    val createdAt: Date,
    val objectId: String,
    val questionText: String,
    val updatedAt: String,
    val assignedDate: QuestionDateApiEntity
)

data class QuestionDateApiEntity(
    val __type: String,
    val iso: Date,
)