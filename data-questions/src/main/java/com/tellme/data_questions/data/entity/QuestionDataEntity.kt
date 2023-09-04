package com.tellme.data_questions.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "questions")
data class QuestionDataEntity(
    @PrimaryKey
    val objectId: String,
    val questionText: String
)
