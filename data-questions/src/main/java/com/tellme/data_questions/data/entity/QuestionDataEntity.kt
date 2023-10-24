package com.tellme.data_questions.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.tellme.data_questions.data.mappers.DateTypeConverter
import java.util.Date

@Entity(tableName = "questions")
@TypeConverters(DateTypeConverter::class)
data class QuestionDataEntity(
    @PrimaryKey
    val objectId: String,
    val questionText: String,
    val createdAt: Date,
    val assignedDate: Date
)
