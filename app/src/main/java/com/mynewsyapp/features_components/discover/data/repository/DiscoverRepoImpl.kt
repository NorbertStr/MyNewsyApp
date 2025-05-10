package com.mynewsyapp.features_components.discover.data.repository

class DiscoverRepoImpl(
    private val discoverApi: DiscoverApi,
    private val database: NewsyArticleDatabase,
    private val mapper: Mapper<DiscoverArticleDto, NewsyArticle>,
) : DiscoverRepository {
    @OptIn(ExperimentalPagingApi::class)
    override fun fetchDiscoverArticles(
        category: String,
        country: String,
        language: String,
    ): Flow<PagingData<NewsyArticle>> {
        return Pager(
            PagingConfig(
                pageSize = K.PAGE_SIZE,
                initialLoadSize = 10,
                prefetchDistance = K.PAGE_SIZE - 1
            ),
            pagingSourceFactory = {
                val data = database.discoverArticleDao().getDiscoverArticleDataSource(category)
                data
            },
            remoteMediator = DiscoverMediator(
                api = discoverApi,
                database = database,
                category = category,
                country = country,
                language = language
            )
        ).flow.map { dtoPager ->
            dtoPager.map { dto ->
                mapper.toModel(dto)
            }
        }
    }

    override suspend fun updateCategory(category: String) {
        database.discoverRemoteKeyDao().updateCategory(category)
    }

    override suspend fun getDiscoverCurrentCategory(): String {
        return database.discoverRemoteKeyDao().getCurrentCategory()
    }

    override suspend fun updateFavouriteDiscoverCategory(article: NewsyArticle) {
        database.discoverArticleDao().updateFavouriteArticle(
            isFavourite = article.favourite,
            id = article.id
        )
    }

    override suspend fun getAllAvailableCategories(): List<String> {
        return database.discoverRemoteKeyDao().getAllAvailableCategories()
    }
}