package com.mynewsyapp.features_components.favourite.domain.use_cases

class GetAllFavouriteUseCase(
    private val repository: FavouriteRepository,
) {
    operator fun invoke(): Flow<PagingData<FavouriteArticle>> =
        repository.getAllFavouriteArticle
}