package com.tellme.data_questions.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tellme.data_questions.domain.entity.Question
import com.tellme.data_questions.network.entity.QuestionApiEntity

@Entity(tableName = "questions")
data class QuestionDataEntity(
    @PrimaryKey
    val objectId: String,
    val questionText: String
) {
    fun toDomain() = Question(objectId = objectId, text = questionText)
}

fun List<QuestionDataEntity>.toDomain() = this.map { it.toDomain() }

fun QuestionApiEntity.toData() = QuestionDataEntity(objectId, questionText)
fun List<QuestionApiEntity>.toData() = this.map { it.toData() }