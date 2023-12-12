package com.example.nikakudirko.pps3.network.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkArticleResponse(
    @SerialName("articles")
    val networkArticles: List<NetworkArticle>,
    @SerialName("status")
    val status: String,
    @SerialName("totalResults")
    val totalResults: Int
)