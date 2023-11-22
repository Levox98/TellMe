package com.tellme.feature_questions

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.tellme.core_navigation.QuestionNavScreen
import com.tellme.feature_questions.screen.QuestionDetailScreen

fun NavGraphBuilder.addQuestionsFeature() {
    navigation(
        startDestination = QuestionNavScreen.QuestionDetails().route,
        route = QuestionNavScreen.Root.route
    ) {
        composable(
            route = QuestionNavScreen.QuestionDetails().route,
            arguments = listOf(navArgument(QuestionNavScreen.QuestionDetails.QUESTION_ID) {
                type = NavType.StringType
            })
        ) {
            QuestionDetailScreen(vm = hiltViewModel())
        }
    }
}