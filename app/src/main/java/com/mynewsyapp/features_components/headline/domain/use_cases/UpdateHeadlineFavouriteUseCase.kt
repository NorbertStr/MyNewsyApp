package com.mynewsyapp.features_components.headline.domain.use_cases

class UpdateHeadlineFavouriteUseCase(
    private val repository: HeadlineRepository,
) {
    suspend operator fun invoke(article: NewsyArticle) {
        repository.updateFavouriteArticle(article)
    }
}