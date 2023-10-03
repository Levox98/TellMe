package com.tellme.feature_main

import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.tellme.core_navigation.QuestionNavScreen
import com.tellme.feature_main.screen.MainScreen

fun NavGraphBuilder.addMainScreen(
    navController: NavHostController
) {
    navigation(
        startDestination = com.tellme.core_navigation.MainNavScreen.Main.route,
        route = com.tellme.core_navigation.MainNavScreen.Root.route
    ) {
        composable(
            route = com.tellme.core_navigation.MainNavScreen.Main.route
        ) {
            MainScreen(
                vm = hiltViewModel(),
                goToDetailScreen = remember {
                    { questionId ->
                        questionId?.let {
                            navController.navigate(
                                QuestionNavScreen.QuestionDetails.createRoute(questionId)
                            )
                        }
                    }
                }
            )
        }
    }
}