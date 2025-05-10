package com.mynewsyapp.features_components.core.domain.repository

import com.mynewsyapp.features_components.core.domain.models.Setting
import com.mynewsyapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface SettingRepository {
    suspend fun getSetting(): Flow<Resource<Setting>>
    suspend fun insertSetting(setting: Setting)
}