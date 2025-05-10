package com.mynewsyapp.features_components.discover.domain.use_cases

class UpdateCurrentCategoryUseCase(
    private val repository: DiscoverRepository,
) {
    suspend operator fun invoke(category: String) {
        repository.updateCategory(category)
    }
}