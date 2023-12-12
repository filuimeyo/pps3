package com.example.nikakudirko.pps3.network

import com.example.nikakudirko.pps3.network.models.NetworkArticle

interface NetworkArticlesRepository {
    suspend fun getNetworkArticles(): Resourse<List<NetworkArticle>>
}