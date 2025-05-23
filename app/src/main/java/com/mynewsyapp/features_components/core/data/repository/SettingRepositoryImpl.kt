package com.mynewsyapp.features_components.core.data.repository

import com.mynewsyapp.features_components.core.data.local.dao.SettingDao
import com.mynewsyapp.features_components.core.data.local.models.SettingsDto
import com.mynewsyapp.features_components.core.domain.mapper.Mapper
import com.mynewsyapp.features_components.core.domain.models.Setting
import com.mynewsyapp.features_components.core.domain.repository.SettingRepository
import com.mynewsyapp.utils.Resource
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class SettingRepositoryImpl(
    private val settingDao: SettingDao,
    private val mapper: Mapper<SettingsDto, Setting>,
) : SettingRepository {
    override suspend fun getSetting(): Flow<Resource<Setting>> = callbackFlow {
        try {
            trySend(Resource.Loading())
            val settingData = settingDao.getSettings()
            val setting = mapper.toModel(settingData)
            trySend(Resource.Success(setting))
        } catch (e: Exception) {
            e.printStackTrace()
            trySend(Resource.Error(e))
        }

        awaitClose { }

    }

    override suspend fun insertSetting(setting: Setting) {
        settingDao.insertSetting(mapper.fromModel(setting))
    }
}