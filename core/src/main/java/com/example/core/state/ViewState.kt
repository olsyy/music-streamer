package com.example.core.state

sealed class ViewState<out T : Any>
data object Loading : ViewState<Nothing>()
data class Success<out T : Any>(val data: T) : ViewState<T>()
data class Error(val code: Int, val message: String) : ViewState<Nothing>()
data class Exception(val e: Throwable) : ViewState<Nothing>()
data object Empty : ViewState<Nothing>()
