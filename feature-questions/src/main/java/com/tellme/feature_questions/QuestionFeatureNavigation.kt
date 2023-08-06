package com.tellme.feature_questions

import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.tellme.feature_questions.screen.QuestionDetailScreen
import com.tellme.feature_questions.screen.QuestionsScreen

fun NavGraphBuilder.addQuestionsFeature(
    navController: NavHostController
) {
    navigation(
        startDestination = QuestionFeatureNavScreen.QuestionsList.route,
        route = QuestionFeatureNavScreen.QuestionsRoot.route
    ) {
        composable(QuestionFeatureNavScreen.QuestionsList.route) {
            QuestionsScreen(
                vm = hiltViewModel(),
                goToDetailScreen = remember {
                    { questionId ->
                        questionId?.let {
                            navController.navigate(
                                QuestionFeatureNavScreen.QuestionDetails.createRoute(questionId)
                            ) { popUpTo(QuestionFeatureNavScreen.QuestionsList.route) }
                        }
                    }
                }
            )
        }

        composable(
            route = QuestionFeatureNavScreen.QuestionDetails.route,
            arguments = listOf(navArgument(QuestionFeatureNavScreen.QuestionDetails.QUESTION_ID) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            QuestionDetailScreen(
                vm = hiltViewModel(),
                questionId = backStackEntry.arguments?.getString(QuestionFeatureNavScreen.QuestionDetails.QUESTION_ID)
            )
        }
    }
}