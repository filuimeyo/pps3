package com.example.nikakudirko.pps3.di

import com.example.nikakudirko.pps3.data.repository.ArticleRepositoryImpl
import com.example.nikakudirko.pps3.domain.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindRepository(repositoryImpl: ArticleRepositoryImpl): Repository


}