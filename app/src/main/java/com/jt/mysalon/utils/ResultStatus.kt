package com.jt.mysalon.utils

sealed class ResultStatus<out T> {

    object Loading : ResultStatus<Nothing>()
    data class Success<out T>(val data: T) : ResultStatus<T>()
    data class Error(val message: HashMap<String, String>) : ResultStatus<Nothing>()

    override fun toString(): String {
        return when (this) {
            Loading -> "Loading"
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[throwable=$message]"
        }
    }
}
