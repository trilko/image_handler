package com.example.image_handler.presentation.model

import com.example.image_handler.presentation.model.ErrorType.*
import java.lang.RuntimeException
import java.net.SocketTimeoutException

enum class ErrorType {

    API,
    NETWORK,
    UNEXPECTED

}

fun Throwable.getErrorType(): ErrorType {
    return when (this) {
        is RuntimeException -> API
        is SocketTimeoutException -> NETWORK
        else -> UNEXPECTED
    }
}