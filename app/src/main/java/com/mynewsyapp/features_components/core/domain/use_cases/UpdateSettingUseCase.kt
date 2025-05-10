package com.mynewsyapp.features_components.core.domain.use_cases

import com.mynewsyapp.features_components.core.domain.models.Setting
import com.mynewsyapp.features_components.core.domain.repository.SettingRepository

class UpdateSettingUseCase(
    private val settingRepository: SettingRepository,
) {
    suspend operator fun invoke(setting: Setting) {
        settingRepository.insertSetting(setting)
    }
}