package com.kenbu.travelapp.utils


sealed class Resource<out T : Any> {
    data class Success<out T : Any>(val data: T) : Resource<T>()
    data class Error<out T : Any>(val message: T) : Resource<T>()
    object Loading : Resource<Nothing>()
}