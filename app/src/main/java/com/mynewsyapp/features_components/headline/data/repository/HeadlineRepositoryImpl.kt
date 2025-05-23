package com.mynewsyapp.features_components.headline.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.mynewsyapp.features_components.core.data.local.NewsyArticleDatabase
import com.mynewsyapp.features_components.core.domain.mapper.Mapper
import com.mynewsyapp.features_components.core.domain.models.NewsyArticle
import com.mynewsyapp.features_components.headline.data.local.model.HeadlineDto
import com.mynewsyapp.features_components.headline.data.paging.HeadlineRemoteMediator
import com.mynewsyapp.features_components.headline.data.remote.HeadlineApi
import com.mynewsyapp.features_components.headline.domain.repository.HeadlineRepository
import com.mynewsyapp.utils.K
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class HeadlineRepositoryImpl(
    private val headlineApi: HeadlineApi,
    private val database: NewsyArticleDatabase,
    private val mapper: Mapper<HeadlineDto, NewsyArticle>,
) : HeadlineRepository {
    @OptIn(ExperimentalPagingApi::class)
    override fun fetchHeadlineArticle(
        category: String,
        country: String,
        language: String,
    ): Flow<PagingData<NewsyArticle>> {
        return Pager(
            PagingConfig(
                pageSize = K.PAGE_SIZE,
                prefetchDistance = K.PAGE_SIZE - 1,
                initialLoadSize = 10
            ),
            remoteMediator = HeadlineRemoteMediator(
                api = headlineApi,
                database = database,
                category = category,
                country = country,
                language = language,
            )
        ) {
            database.headlineDao().getAllHeadlineArticles()
        }.flow.map { dtoPager ->
            dtoPager.map { dto ->
                mapper.toModel(dto)
            }
        }
    }

    override suspend fun updateFavouriteArticle(newsyArticle: NewsyArticle) {
        database.headlineDao().updateFavouriteArticle(
            isFavourite = newsyArticle.favourite,
            id = newsyArticle.id
        )
    }
}