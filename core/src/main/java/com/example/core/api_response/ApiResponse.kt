package com.example.core.api_response

sealed class ApiResponse<out T : Any>
data object Empty : ApiResponse<Nothing>()
data object Loading : ApiResponse<Nothing>()
data class Success<out T : Any>(val data: T) : ApiResponse<T>()
data class Error(val code: Int, val message: String) : ApiResponse<Nothing>()
data class Exception(val e: Throwable) : ApiResponse<Nothing>()