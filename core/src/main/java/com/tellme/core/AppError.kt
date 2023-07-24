package com.tellme.core

sealed class AppError(val code: Int, private val message: String = "") {

    class Unknown(message: String = "") : AppError(0, message)

    companion object {

        fun error(code: Int, message: String = ""): AppError = when (code) {
            else -> Unknown(message)
        }
    }

    fun message() = message.ifEmpty {
        when (code) {
            else -> "Unknown error."
        }
    }
}