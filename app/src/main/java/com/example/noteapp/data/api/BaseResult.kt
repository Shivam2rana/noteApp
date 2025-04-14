package com.example.noteapp.data.api

sealed class BaseResult<T>(
    val data: T? = null,
    val message: String? = null,
    val statusCode: Int? = null
) {
    class Success<T>(data: T, message: String, statusCode: Int) :
        BaseResult<T>(data, message, statusCode)

    class Error<T>(message: String, statusCode: Int) :
        BaseResult<T>(message = message, statusCode = statusCode)

    class Loading<T> : BaseResult<T>()
    class Idle<T> : BaseResult<T>()
}
