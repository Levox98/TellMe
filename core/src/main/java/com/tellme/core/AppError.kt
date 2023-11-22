package com.tellme.core

import android.util.Log

/**
 * Class that holds data about errors that were caused by app-specific components. Also handles
 * logging custom error messages
 * @param code Int value representing abstract error code
 * @param message error message
 */
sealed class AppError(val code: Int, val message: String = "") {

    class Unknown(message: String = "") : AppError(0, message)
    class Navigation(message: String = "Unknown") : AppError(
        code = AppErrorData.Navigation.code,
        message = message
    )

    companion object {
        const val UNKNOWN_ERROR_LOG_TAG = "UNKNOWN_ERROR"
        const val NAV_ERROR_LOG_TAG = "NAV_ERROR"

        /**
         * Method used for logging and returning errors specific to the app logic
         * @param code error code
         * @param message error message, don't forget to describe the error here
         * @return specific AppError sub-type that has info on the error that occurred
         * @see AppError
         */
        fun error(code: Int, message: String = "Empty message"): AppError {
            val data = when (code) {
                AppErrorData.Navigation.code -> AppErrorData.Navigation
                else -> AppErrorData.Unknown
            }

            val concatMessage = data.prefix + message

            log(data.tag, concatMessage)

            return when (data) {
                AppErrorData.Navigation -> Navigation(concatMessage)
                else -> Unknown(concatMessage)
            }
        }

        private fun log(tag: String, message: String) {
            Log.e(tag, message)
        }
    }
}

/**
 * Enum class that holds basic data about errors
 * @param code error code
 * @param prefix prefix for error message, for clearer indication of the error type
 * @param tag logging tag
 */
enum class AppErrorData(val code: Int, val prefix: String, val tag: String) {
    Unknown(code = -1, prefix = "Unknown error: ", tag = AppError.UNKNOWN_ERROR_LOG_TAG),
    Navigation(code = 1, prefix = "Navigation error: ", tag = AppError.NAV_ERROR_LOG_TAG)
}
