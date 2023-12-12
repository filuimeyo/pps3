package com.example.nikakudirko.pps3.network

import com.example.nikakudirko.pps3.network.models.NetworkArticle
import com.example.nikakudirko.pps3.network.models.NetworkArticleResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.*
import javax.inject.Inject

private const val NETWORK_ARTICLES_URL = "https://newsapi.org/v2/top-headlines?country=us&apiKey=4a2790a7ad6241649361fda3cb5841fa"

class NetworkArticlesRepositoryImpl @Inject constructor(
    private val httpClient: HttpClient
) : NetworkArticlesRepository {
    override suspend fun getNetworkArticles(): Resourse<List<NetworkArticle>> {
        return try{
            Resourse.Success(
                httpClient.get<NetworkArticleResponse>{
                    url(NETWORK_ARTICLES_URL)
                }.networkArticles
            )
        } catch (e: Exception){
            e.printStackTrace()
            Resourse.Failure(e)
        }
    }
}