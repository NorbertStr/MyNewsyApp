package com.mynewsyapp.features_components.discover.domain.use_cases

import com.mynewsyapp.features_components.discover.domain.repository.DiscoverRepository

class UpdateCurrentCategoryUseCase(
    private val repository: DiscoverRepository,
) {
    suspend operator fun invoke(category: String) {
        repository.updateCategory(category)
    }
}