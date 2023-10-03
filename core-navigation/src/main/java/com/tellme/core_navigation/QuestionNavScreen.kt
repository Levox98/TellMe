package com.tellme.core_navigation

sealed class QuestionNavScreen {
    abstract val route: String

    object Root : QuestionNavScreen() {
        override val route: String
            get() = "questions_root"
    }

    object QuestionDetails : QuestionNavScreen() {
        const val QUESTION_ID = "questionId"

        override val route: String
            get() = "question/{$QUESTION_ID}"

        fun createRoute(questionId: String) = "question/$questionId"
    }

}