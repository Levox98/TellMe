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
        startDestination = "questions_list",
        route = "questions_start"
    ) {
        composable("questions_list") {
            QuestionsScreen(
                vm = hiltViewModel(),
                goToDetailScreen = remember { { questionId ->
                    questionId?.let {
                        navController.navigate("question/$questionId") { popUpTo("questions_list") }
                    }
                } }
            )
        }

        composable(
            route = "question/{questionId}",
            arguments = listOf(navArgument("questionId") { type = NavType.StringType })
        ) { backStackEntry ->
            QuestionDetailScreen(
                vm = hiltViewModel(),
                questionId = backStackEntry.arguments?.getString("questionId")
            )
        }
    }
}