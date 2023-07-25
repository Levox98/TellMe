package com.tellme.data_questions.data.entity

import com.tellme.data_questions.domain.entity.Question
import com.tellme.data_questions.network.entity.QuestionApiEntity

data class QuestionDataEntity(
    val objectId: String,
    val questionText: String
) {
    fun toDomain() = Question(objectId = objectId, text = questionText)
}

fun List<QuestionDataEntity>.toDomain() = this.map { it.toDomain() }

fun QuestionApiEntity.toDomain() = Question(objectId, questionText)
fun List<QuestionApiEntity>.toDomain() = this.map { it.toDomain() }