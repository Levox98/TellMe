package com.tellme.data_questions.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tellme.data_questions.data.entity.QuestionDataEntity

@Dao
interface QuestionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestions(questions: List<QuestionDataEntity>)

    @Query("SELECT * FROM questions")
    suspend fun getQuestions(): List<QuestionDataEntity>

    @Query("SELECT * FROM questions WHERE objectId = :questionId")
    suspend fun getQuestionById(questionId: String): QuestionDataEntity?
}