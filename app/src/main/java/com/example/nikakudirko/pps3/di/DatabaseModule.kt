package com.example.nikakudirko.pps3.di

import android.content.Context
import androidx.room.Room
import com.example.nikakudirko.pps3.data.local.ArticleDao
import com.example.nikakudirko.pps3.data.local.ArticleDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideArticleDao(database: ArticleDatabase):ArticleDao =
        database.articleDao

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): ArticleDatabase = Room.databaseBuilder(
        context,
        ArticleDatabase::class.java,
        "articles_db"
    )
        .build()
}