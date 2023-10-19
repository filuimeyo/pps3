package com.example.nikakudirko.pps3.data.repository

import com.example.nikakudirko.pps3.data.local.ArticleDao
import com.example.nikakudirko.pps3.data.local.model.Article
import com.example.nikakudirko.pps3.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ArticleRepositoryImpl @Inject constructor(
    private val articleDao: ArticleDao
) : Repository {
    override fun getAllArticles(): Flow<List<Article>> {
       return articleDao.getAllArticles()
    }

    override fun getArticleById(id: Long): Flow<Article> {
       return articleDao.getArticleById(id)
    }

    override suspend fun insert(article: Article) {
        articleDao.insertArticle(article)
    }

    override suspend fun update(article: Article) {
        articleDao.updateArticle(article)
    }

    override suspend fun delete(id: Long) {
        articleDao.delete(id)
    }

    override fun getBookMarkedArticles(): Flow<List<Article>> {
        return articleDao.getBookMarkedArticles()
    }

}