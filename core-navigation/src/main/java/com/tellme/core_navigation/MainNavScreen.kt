package com.tellme.core_navigation

sealed class MainNavScreen : BaseNav() {

    object Root : MainNavScreen() {
        override val route: String
            get() = "main_root"
    }

    object Main : MainNavScreen() {
        override val route: String
            get() = "main_screen"
    }
}