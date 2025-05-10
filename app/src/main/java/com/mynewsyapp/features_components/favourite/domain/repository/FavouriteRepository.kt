package com.mynewsyapp.features_components.favourite.domain.repository

interface FavouriteRepository {
    val getAllFavouriteArticle: Flow<PagingData<FavouriteArticle>>
}