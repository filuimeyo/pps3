package com.example.nikakudirko.pps3.domain.repository

import com.example.nikakudirko.pps3.data.local.model.Article
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getAllArticles(): Flow<List<Article>>
    fun getArticleById(id: Long): Flow<Article>
    suspend fun insert(article: Article)
    suspend fun update(article: Article)
    suspend fun delete(id: Long)
    fun getBookMarkedArticles(): Flow<List<Article>>
}