package com.example.scannerapp

sealed class Resources<T>(val Data: T? = null, val message: String? =null){
    class Success<T>(Data: T): Resources<T>(Data)
    class Error<T>(message: String, Data: T? = null): Resources<T>(Data, message)
    class Loading<T>(Data: T? = null): Resources<T>(Data)
}
