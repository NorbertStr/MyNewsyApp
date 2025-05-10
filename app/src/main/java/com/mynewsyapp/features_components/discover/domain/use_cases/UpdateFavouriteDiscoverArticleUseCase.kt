package com.mynewsyapp.features_components.discover.domain.use_cases

import com.mynewsyapp.features_components.core.domain.models.NewsyArticle
import com.mynewsyapp.features_components.discover.domain.repository.DiscoverRepository

class UpdateFavouriteDiscoverArticleUseCase(
    private val repository: DiscoverRepository,
) {
    suspend operator fun invoke(article: NewsyArticle) {
        repository.updateFavouriteDiscoverCategory(article)
    }
}