package com.debicki.mdbpreview.network

sealed class Response<T> {
    data class Success<T>(val value: T) : Response<T>()
    data class Error<T>(val error: String) : Response<T>()
}