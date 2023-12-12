package com.example.nikakudirko.pps3.di

import com.example.nikakudirko.pps3.network.HttpClient
import com.example.nikakudirko.pps3.network.NetworkArticlesRepository
import com.example.nikakudirko.pps3.network.NetworkArticlesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class AppModule {
    @Provides
    fun getHttpClient(httpClient: HttpClient): io.ktor.client.HttpClient = httpClient.getHttpClient()

    @Provides
    fun getMoviesRepository(impl: NetworkArticlesRepositoryImpl): NetworkArticlesRepository = impl
}