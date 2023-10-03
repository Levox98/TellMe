package com.tellme.feature_questions

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.tellme.core_navigation.QuestionFeatureNavScreen
import com.tellme.feature_questions.screen.QuestionDetailScreen

fun NavGraphBuilder.addQuestionsFeature() {
    navigation(
        startDestination = QuestionFeatureNavScreen.QuestionDetails.route,
        route = QuestionFeatureNavScreen.Root.route
    ) {
        composable(
            route = QuestionFeatureNavScreen.QuestionDetails.route,
            arguments = listOf(navArgument(QuestionFeatureNavScreen.QuestionDetails.QUESTION_ID) {
                type = NavType.StringType
            })
        ) {
            QuestionDetailScreen(vm = hiltViewModel())
        }
    }
}