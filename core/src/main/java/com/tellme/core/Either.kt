package com.tellme.core

class Either<out T>(
    val status: Status,
    val data: T? = null,
    val error: AppError? = null,
) {
    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T?): Either<T> =
            Either(
                Status.SUCCESS,
                data
            )

        fun <T> loading(): Either<T> =
            Either(
                Status.LOADING
            )

        fun <T> error(code: Int, info: String = "", message: String = ""): Either<T> =
            Either(
                status = Status.ERROR,
                error = AppError.error(code, message)
            )

        fun <T> error(error: AppError): Either<T> =
            Either(
                status = Status.ERROR,
                error = error
            )

        fun none() = Either(
            status = Either.Status.SUCCESS,
            data = Either.None()
        )
    }

    fun isError() = status == Status.ERROR
    fun isSuccess() = status == Status.SUCCESS
    fun isLoading() = status == Status.LOADING

    suspend fun onError(callBack: suspend (error: AppError) -> Unit) {
        if (isError()) {
            callBack(error!!)
        }
    }

    suspend fun onLoading(callBack: suspend () -> Unit) {
        if (isLoading()) {
            callBack()
        }
    }

    suspend fun onSuccess(callBack: suspend (data: T?) -> Unit) {
        if (isSuccess()) {
            callBack(data)
        }
    }

    class None {}
}

typealias EitherNone = Either<Either.None>