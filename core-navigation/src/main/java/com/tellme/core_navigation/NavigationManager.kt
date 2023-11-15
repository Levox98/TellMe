package com.tellme.core_navigation

import android.util.Log
import androidx.navigation.NavHostController
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavigationManager @Inject constructor() {

    private var navController: NavHostController? = null

    fun navigate(destination: BaseNav) {
        try {
            navController?.navigate(destination.route)
        } catch (e: Exception) {
            Log.e(NAV_LOG_TAG, "Tried navigating to ${destination.route} with exception: ${e.localizedMessage}")
        }
    }

    fun navigateBack() {
        navController?.popBackStack()
    }

    fun initialize(navController: NavHostController) {
        this.navController = navController
    }

    companion object {
        private const val NAV_LOG_TAG = "LOG_NAV"
    }
}
