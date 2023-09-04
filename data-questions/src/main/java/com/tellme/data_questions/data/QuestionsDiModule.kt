package com.tellme.data_questions.data

import android.content.Context
import androidx.room.Room
import com.tellme.data_questions.data.db.QuestionDatabase
import com.tellme.data_questions.domain.QuestionsRepository
import com.tellme.data_questions.network.QuestionsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object QuestionsDiModule {

    @Provides
    @Singleton
    internal fun provideDb(@ApplicationContext context: Context): QuestionDatabase =
        Room.databaseBuilder(context, QuestionDatabase::class.java, "question_db")
            .build()

    @Provides
    @Singleton
    internal fun provideQuestionsDao(db: QuestionDatabase) = db.questionDao()

    @Provides
    @Singleton
    internal fun bindQuestionsRepository(r: QuestionsRepositoryImpl): QuestionsRepository = r

    @Provides
    @Singleton
    internal fun provideQuestionsService(retrofit: Retrofit): QuestionsApiService =
        retrofit.create(QuestionsApiService::class.java)
}