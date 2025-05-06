package com.mynewsyapp.features_components.detail.domain.use_cases

import com.mynewsyapp.features_components.detail.domain.models.DetailArticle
import com.mynewsyapp.features_components.detail.domain.repository.DetailRepository
import com.mynewsyapp.utils.Resource
import kotlinx.coroutines.flow.Flow

class GetDetailDiscoverArticleUseCase(
    private val detailRepository: DetailRepository,
) {
    suspend operator fun invoke(id: Int): Flow<Resource<DetailArticle>> =
        detailRepository.getDiscoverArticleById(id)
}