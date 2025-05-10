package com.mynewsyapp.features_components.discover.domain.use_cases

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