package com.mynewsyapp.features_components.search.data.remote.models


import com.mynewsyapp.features_components.search.data.remote.models.Article
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchArticleRemoteDto(
    @SerialName("articles")
    val articles: List<Article> = listOf(),
    @SerialName("status")
    val status: String = "",
    @SerialName("totalResults")
    val totalResults: Int = 0
)