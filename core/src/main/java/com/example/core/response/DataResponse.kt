package com.example.core.response

sealed class DataResponse<out T : Any>
data object Loading : DataResponse<Nothing>()
data class Success<out T : Any>(val data: T) : DataResponse<T>()
data class Error(val code: Int, val message: String) : DataResponse<Nothing>()
data class Exception(val e: Throwable) : DataResponse<Nothing>()