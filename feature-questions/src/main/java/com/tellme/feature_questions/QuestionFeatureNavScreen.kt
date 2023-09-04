package com.tellme.feature_questions

sealed class QuestionFeatureNavScreen {
    abstract val route: String

    object QuestionsRoot : QuestionFeatureNavScreen() {
        override val route: String
            get() = "questions_start"
    }

    object QuestionsList : QuestionFeatureNavScreen() {
        override val route: String
            get() = "questions_list"
    }

    object QuestionDetails : QuestionFeatureNavScreen() {
        const val QUESTION_ID = "questionId"

        override val route: String
            get() = "question/{$QUESTION_ID}"

        fun createRoute(questionId: String) = "question/$questionId"
    }

}