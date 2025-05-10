package com.mynewsyapp.features_components.discover.domain.use_cases

class UpdateFavouriteDiscoverArticleUseCase(
    private val repository: DiscoverRepository,
) {
    suspend operator fun invoke(article: NewsyArticle) {
        repository.updateFavouriteDiscoverCategory(article)
    }
}