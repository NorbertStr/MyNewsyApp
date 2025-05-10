package com.mynewsyapp.features_components.discover.domain.use_cases

import androidx.paging.PagingData
import com.mynewsyapp.features_components.core.domain.models.NewsyArticle
import com.mynewsyapp.features_components.discover.domain.repository.DiscoverRepository
import kotlinx.coroutines.flow.Flow

class FetchDiscoverArticleUseCase(
    private val repository: DiscoverRepository
) {
    operator fun invoke(
        category:String,
        language:String,
        country:String
    ):Flow<PagingData<NewsyArticle>> =
        repository.fetchDiscoverArticles(category, country, language)
}








