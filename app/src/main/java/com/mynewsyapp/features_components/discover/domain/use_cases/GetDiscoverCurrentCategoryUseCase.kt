package com.mynewsyapp.features_components.discover.domain.use_cases

import com.mynewsyapp.features_components.discover.domain.repository.DiscoverRepository

class GetDiscoverCurrentCategoryUseCase(
    private val repository: DiscoverRepository,
) {
    suspend operator fun invoke(): String {
        return repository.getDiscoverCurrentCategory()
    }
}