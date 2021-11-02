package com.example.image_handler.presentation.model

data class ErrorUI(
    val message: String,
    val type: ErrorType
)

fun Throwable.mapToErrorUI(message: String): ErrorUI {
    return ErrorUI(
        message = message,
        type = getErrorType()
    )
}