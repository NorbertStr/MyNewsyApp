package com.mynewsyapp.features_components.headline.domain.use_cases

import androidx.paging.PagingData
import com.mynewsyapp.features_components.core.domain.models.NewsyArticle
import com.mynewsyapp.features_components.headline.domain.repository.HeadlineRepository
import kotlinx.coroutines.flow.Flow

class FetchHeadlineArticleUseCase(
    private val repository: HeadlineRepository,
) {
    operator fun invoke(
        category: String,
        countryCode: String,
        languageCode: String,
    ): Flow<PagingData<NewsyArticle>> = repository.fetchHeadlineArticle(
        category, countryCode, languageCode
    )
}