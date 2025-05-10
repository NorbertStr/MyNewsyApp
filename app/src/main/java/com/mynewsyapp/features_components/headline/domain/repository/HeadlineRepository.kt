package com.mynewsyapp.features_components.headline.domain.repository

interface HeadlineRepository {
    fun fetchHeadlineArticle(
        category: String,
        country: String,
        language: String,
    ): Flow<PagingData<NewsyArticle>>

    suspend fun updateFavouriteArticle(newsyArticle: NewsyArticle)
}