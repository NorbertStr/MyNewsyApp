package com.mynewsyapp.features_components.favourite.domain.use_cases

import androidx.paging.PagingData
import com.mynewsyapp.features_components.favourite.domain.model.FavouriteArticle
import com.mynewsyapp.features_components.favourite.domain.repository.FavouriteRepository
import kotlinx.coroutines.flow.Flow

class GetAllFavouriteUseCase(
    private val repository: FavouriteRepository,
) {
    operator fun invoke(): Flow<PagingData<FavouriteArticle>> =
        repository.getAllFavouriteArticle
}