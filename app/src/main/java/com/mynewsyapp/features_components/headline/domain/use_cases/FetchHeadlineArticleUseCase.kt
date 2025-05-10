package com.mynewsyapp.features_components.headline.domain.use_cases

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