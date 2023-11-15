package com.tellme.feature_main

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.tellme.core_navigation.MainNavScreen
import com.tellme.feature_main.screen.MainScreen

fun NavGraphBuilder.addMainScreen() {
    navigation(
        startDestination = MainNavScreen.Main.route,
        route = MainNavScreen.Root.route
    ) {
        composable(
            route = MainNavScreen.Main.route
        ) {
            MainScreen(
                vm = hiltViewModel()
            )
        }
    }
}