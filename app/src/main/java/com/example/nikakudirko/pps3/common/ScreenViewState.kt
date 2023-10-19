package com.example.nikakudirko.pps3.common

sealed class ScreenViewState<out T> {
    object Loading:ScreenViewState<Nothing>()
    data class Success<T>(val data:T):ScreenViewState<T>()
    data class Error(val message:String?):ScreenViewState<Nothing>()
}