package com.example.nikakudirko.pps3.network

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.DefaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.features.observer.ResponseObserver
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import kotlinx.serialization.json.Json
import javax.inject.Inject

class HttpClient @Inject constructor() {
    fun getHttpClient() = HttpClient(Android){
        install(JsonFeature){
            serializer = KotlinxSerializer(Json{
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }

        install(Logging){
            logger = object :  Logger{
                override fun log(message: String) {
                    Log.i(TAG_KTOR_LOGGER, message)
                }

            }
        }

        install(ResponseObserver){
            onResponse { response->
                Log.i(TAG_HTTP_STATUS_LOGGER, "${response.status.value}")
            }
        }

        install(DefaultRequest){
            header(HttpHeaders.ContentType, ContentType.Application.Json)
           // parameter("api_key", "4a2790a7ad6241649361fda3cb5841fa")

        }

        engine {
            connectTimeout = TIME_OUT
            socketTimeout = TIME_OUT
        }
    }

    companion object{
        private const val TIME_OUT = 10_000
        private const val TAG_KTOR_LOGGER = "ktor_logger:"
        private const val TAG_HTTP_STATUS_LOGGER = "http_status:"
    }
}