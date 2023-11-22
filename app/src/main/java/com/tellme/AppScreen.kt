package com.tellme

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.tellme.core_navigation.MainNavScreen
import com.tellme.core_navigation.NavigationManager
import com.tellme.core_ui.theme.AppTheme
import com.tellme.feature_main.addMainScreen
import com.tellme.feature_questions.addQuestionsFeature

@Composable
fun AppScreen(navigationManager: NavigationManager) {
    val navController = rememberNavController()
    navigationManager.initialize(navController)

    AppTheme {
        NavHost(
            navController = navController,
            startDestination = MainNavScreen.Root.route
        ) {
            addMainScreen()
            addQuestionsFeature()
        }
    }
}