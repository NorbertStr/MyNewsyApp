package com.mynewsyapp.features_components.favourite.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.mynewsyapp.features_components.core.domain.mapper.Mapper
import com.mynewsyapp.features_components.favourite.data.dao.FavouriteDao
import com.mynewsyapp.features_components.favourite.data.models.FavouriteDto
import com.mynewsyapp.features_components.favourite.domain.model.FavouriteArticle
import com.mynewsyapp.features_components.favourite.domain.repository.FavouriteRepository
import com.mynewsyapp.utils.K
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavouriteRepoImpl(
    private val favouriteDao: FavouriteDao,
    private val mapper: Mapper<FavouriteDto, FavouriteArticle>,
) : FavouriteRepository {
    override val getAllFavouriteArticle: Flow<PagingData<FavouriteArticle>>
        get() = Pager(
            PagingConfig(
                pageSize = K.PAGE_SIZE,
                prefetchDistance = K.PAGE_SIZE - 1,
                initialLoadSize = 10
            )
        ) {
            favouriteDao.getAllFavouriteArticles()
        }.flow.map { dtoPager ->
            dtoPager.map { dto ->
                mapper.toModel(dto)
            }
        }
}