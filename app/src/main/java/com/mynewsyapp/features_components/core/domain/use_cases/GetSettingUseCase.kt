package com.mynewsyapp.features_components.core.domain.use_cases

import com.mynewsyapp.features_components.core.domain.models.Setting
import com.mynewsyapp.features_components.core.domain.repository.SettingRepository
import com.mynewsyapp.utils.Resource
import kotlinx.coroutines.flow.Flow

class GetSettingUseCase(
    private val settingRepository: SettingRepository,
) {
    suspend operator fun invoke(): Flow<Resource<Setting>> {
        return settingRepository.getSetting()
    }
}