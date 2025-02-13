package com.pourkazemi.mahdi.dua_bayadyar.util

import android.util.Log

private const val TAG = "AppError"

sealed class AppError(message: String) : Exception(message) {
    object NetworkError : AppError("Network connection error")
    class NotFoundError(message: String) : AppError(message)
    class DatabaseError(cause: Throwable) : AppError("Database error: ${cause.message}")
}

fun handleError(error: AppError): String {
    val message = when (error) {
        is AppError.DatabaseError -> "Database error occurred"
        is AppError.NetworkError -> "Network error occurred"
       // is AppError.ValidationError -> "Invalid ${error.field}: ${error.message}"
        is AppError.NotFoundError -> "Item not found"
    }
    
    Log.e(TAG, message, error)
    return message
} 