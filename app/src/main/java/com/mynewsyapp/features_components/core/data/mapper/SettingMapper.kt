package com.mynewsyapp.features_components.core.data.mapper

import com.mynewsyapp.features_components.core.data.local.models.SettingsDto
import com.mynewsyapp.features_components.core.domain.mapper.Mapper
import com.mynewsyapp.features_components.core.domain.models.Setting

class SettingMapper : Mapper<SettingsDto?, Setting> {
    override fun toModel(value: SettingsDto?): Setting {
        return value?.run {
            Setting(
                preferredCountryIndex = preferredCountry ?: 0,
                preferredLanguageIndex = preferredLanguage ?: 0
            )
        } ?: Setting(
            preferredLanguageIndex = 0,
            preferredCountryIndex = 0
        )
    }

    override fun fromModel(value: Setting): SettingsDto {
        return value.run {
            SettingsDto(
                preferredLanguage = preferredLanguageIndex,
                preferredCountry = preferredCountryIndex
            )
        }
    }
}