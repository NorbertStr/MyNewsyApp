package com.mynewsyapp.features_components.favourite.data.repository

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