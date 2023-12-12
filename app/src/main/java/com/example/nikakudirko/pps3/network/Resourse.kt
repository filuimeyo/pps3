package com.example.nikakudirko.pps3.network

import java.lang.Exception

sealed class Resourse<out R> {
    data class Success<out R>(val result: R): Resourse<R>()
    data class Failure(val exception: Exception): Resourse<Nothing>()
    object Loading: Resourse<Nothing>()
}