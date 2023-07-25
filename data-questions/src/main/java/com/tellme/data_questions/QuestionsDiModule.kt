package com.tellme.data_questions

import com.tellme.data_questions.data.QuestionsRepositoryImpl
import com.tellme.data_questions.domain.QuestionsRepository
import com.tellme.data_questions.network.QuestionsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object QuestionsDiModule {

    @Provides
    @Singleton
    fun bindQuestionsRepository(r: QuestionsRepositoryImpl): QuestionsRepository = r

    @Provides
    @Singleton
    fun provideQuestionsService(retrofit: Retrofit): QuestionsApiService =
        retrofit.create(QuestionsApiService::class.java)
}