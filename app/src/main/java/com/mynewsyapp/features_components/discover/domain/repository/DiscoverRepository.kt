package com.mynewsyapp.features_components.discover.domain.repository

interface DiscoverRepository {
    fun fetchDiscoverArticles(
        category: String,
        country: String,
        language: String,
    ): Flow<PagingData<NewsyArticle>>

    suspend fun updateCategory(category: String)
    suspend fun getDiscoverCurrentCategory(): String
    suspend fun updateFavouriteDiscoverCategory(article: NewsyArticle)
    suspend fun getAllAvailableCategories(): List<String>
}