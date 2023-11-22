package com.tellme.core_navigation

sealed class QuestionNavScreen : BaseNav() {

    object Root : QuestionNavScreen() {
        override val route: String
            get() = "questions_root"
    }

    class QuestionDetails(private val questionId: String? = null) : QuestionNavScreen() {

        override val route: String
            get() = "question/${questionId ?: "{$QUESTION_ID}"}"

        companion object {
            const val QUESTION_ID = "questionId"
        }
    }
}