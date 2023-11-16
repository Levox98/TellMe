package com.tellme.core_navigation

import android.util.Log
import androidx.navigation.NavHostController
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Class that handles basic navigation logic
 */
@Singleton
class NavigationManager @Inject constructor() {

    private var navController: NavHostController? = null

    /**
     * Method for navigating to a destination
     * @param destination a BaseNav object for the desired destination, the object must have
     * its own 'route' value to navigate properly
     * @see BaseNav
     */
    fun navigate(destination: BaseNav) {
        when {
            navController == null -> {
                Log.e(NAV_LOG_TAG, "Navigation manager NavHostController instance is null")
                return
            }

            destination.route == "" -> {
                Log.e(NAV_LOG_TAG, "The navigation destination has no route")
                return
            }

            destination.route.isNotEmpty() -> {
                try {
                    navController!!.navigate(destination.route) {
                        popUpTo(destination.route) { inclusive = destination.first }
                    }
                } catch (e: Exception) {
                    Log.e(NAV_LOG_TAG, "Couldn't navigate to route: ${destination.route}. Error: ${e.message}")
                }
            }
        }

        try {
            navController?.navigate(destination.route)
        } catch (e: Exception) {
            Log.e(
                NAV_LOG_TAG,
                "Tried navigating to ${destination.route} with exception: ${e.localizedMessage}"
            )
        }
    }

    /**
     * Method for navigating to previous destination(relative to the current destination)
     */
    fun navigateBack() {
        navController?.popBackStack()
    }

    /**
     * Method for initializing the NavigationManager
     * @param navController NavHostController instance that is used for navigation in the app.
     * Multiple NavHostController instances not supported, obviously
     */
    fun initialize(navController: NavHostController) {
        this.navController = navController
    }

    companion object {
        private const val NAV_LOG_TAG = "LOG_NAV"
    }
}
