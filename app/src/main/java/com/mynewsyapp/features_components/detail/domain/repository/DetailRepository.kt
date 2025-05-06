package com.mynewsyapp.features_components.detail.domain.repository

import com.mynewsyapp.features_components.detail.domain.models.DetailArticle
import com.mynewsyapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface DetailRepository {
    suspend fun getHeadlineArticleById(id: Int): Flow<Resource<DetailArticle>>
    suspend fun getDiscoverArticleById(id: Int): Flow<Resource<DetailArticle>>
    suspend fun getSearchArticleById(id: Int): Flow<Resource<DetailArticle>>
}