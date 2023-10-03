package com.tellme.core_navigation

sealed class QuestionFeatureNavScreen {
    abstract val route: String

    object Root : QuestionFeatureNavScreen() {
        override val route: String
            get() = "questions_root"
    }

    object QuestionDetails : QuestionFeatureNavScreen() {
        const val QUESTION_ID = "questionId"

        override val route: String
            get() = "question/{$QUESTION_ID}"

        fun createRoute(questionId: String) = "question/$questionId"
    }

}