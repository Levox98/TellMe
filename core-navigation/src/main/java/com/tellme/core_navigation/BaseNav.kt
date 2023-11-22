package com.tellme.core_navigation

/**
 * @property route string representation of destination's route
 * @property first a value that determines whether the backstack should be cleared when navigating
 * to the destination
 */
abstract class BaseNav {

    open val route: String = ""
    open val first: Boolean = false
}