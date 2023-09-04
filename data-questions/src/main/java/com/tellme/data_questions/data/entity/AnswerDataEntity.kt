package com.tellme.data_questions.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.tellme.data_questions.data.mappers.DateTypeConverter
import java.util.Date

@Entity(tableName = "answers")
@TypeConverters(DateTypeConverter::class)
data class AnswerDataEntity(
    @PrimaryKey
    val questionId: String,
    val answerText: String,
    val answerDate: Date,
)
