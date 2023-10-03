package com.tellme.data_questions.data.mappers

import com.tellme.data_questions.data.entity.QuestionDataEntity
import com.tellme.data_questions.domain.entity.Question
import com.tellme.data_questions.network.entity.QuestionApiEntity


fun QuestionDataEntity.toDomain() =
    Question(objectId = objectId, text = questionText, assignedDate = assignedDate)

fun List<QuestionDataEntity>.toDomain() = this.map { it.toDomain() }

fun QuestionApiEntity.toData() =
    QuestionDataEntity(
        objectId = objectId,
        questionText = questionText,
        createdAt = createdAt,
        assignedDate = assignedDate.iso
    )

fun List<QuestionApiEntity>.toData() = this.map { it.toData() }