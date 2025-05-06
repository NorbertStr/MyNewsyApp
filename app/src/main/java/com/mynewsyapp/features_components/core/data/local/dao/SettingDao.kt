package com.mynewsyapp.features_components.core.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mynewsyapp.features_components.core.data.local.models.SettingsDto

interface SettingDao {
    @Dao
    interface SettingDao {
        @Query("SELECT * FROM setting_table")
        suspend fun getSettings(): SettingsDto

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insertSetting(settingsDto: SettingsDto)
    }
}