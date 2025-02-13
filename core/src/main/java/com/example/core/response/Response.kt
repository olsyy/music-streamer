package com.example.core.response

sealed class Response<out T : Any>
data object Loading : Response<Nothing>()
data class Success<out T : Any>(val data: T) : Response<T>()
data class Error(val code: Int, val message: String) : Response<Nothing>()
data class Exception(val e: Throwable) : Response<Nothing>()