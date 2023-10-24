package com.tellme.data_questions.data.mappers

import com.tellme.data_questions.data.entity.AnswerDataEntity
import com.tellme.data_questions.domain.entity.Answer

fun Answer.toData(questionId: String) =
    AnswerDataEntity(questionId = questionId, answerText = answerText, answerDate = answerDate)

fun AnswerDataEntity.toDomain() = Answer(answerText = answerText, answerDate = answerDate)