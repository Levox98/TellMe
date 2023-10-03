package com.tellme

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.tellme.core_ui.theme.AppTheme
import com.tellme.feature_questions.addQuestionsFeature

@Composable
fun AppScreen() {
    val navController = rememberNavController()

    AppTheme {
        NavHost(navController = navController, startDestination = "questions_start") {
            addQuestionsFeature(navController)
        }
    }
}