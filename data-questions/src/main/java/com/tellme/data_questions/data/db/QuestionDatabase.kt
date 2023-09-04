package com.tellme.data_questions.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tellme.data_questions.data.entity.AnswerDataEntity
import com.tellme.data_questions.data.entity.QuestionDataEntity

@Database(
    entities = [
        QuestionDataEntity::class,
        AnswerDataEntity::class
    ],
    version = 1,
    exportSchema = false
)
internal abstract class QuestionDatabase : RoomDatabase() {
    abstract fun questionDao(): QuestionDao
}