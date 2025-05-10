package com.mynewsyapp.features_components.core.data.remote.models


import com.mynewsyapp.features_components.core.data.remote.models.Article
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsyRemoteDto(
    @SerialName("articles")
    val articles: List<Article> = listOf(),
    @SerialName("status")
    val status: String = "",
    @SerialName("totalResults")
    val totalResults: Int = 0
)