package com.tellme.core_navigation

sealed class MainNavScreen {
    abstract val route: String

    object Root : MainNavScreen() {
        override val route: String
            get() = "main_root"
    }

    object Main : MainNavScreen() {
        override val route: String
            get() = "main_screen"
    }
}