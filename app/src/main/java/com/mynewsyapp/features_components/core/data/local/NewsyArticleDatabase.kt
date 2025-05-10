package com.mynewsyapp.features_components.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mynewsyapp.features_components.core.data.local.dao.SettingDao
import com.mynewsyapp.features_components.core.data.local.models.SettingsDto
import com.mynewsyapp.features_components.detail.data.dao.DetailDao
import com.mynewsyapp.features_components.discover.data.local.dao.DiscoverArticleDao
import com.mynewsyapp.features_components.discover.data.local.dao.DiscoverRemoteKeyDao
import com.mynewsyapp.features_components.discover.data.local.models.DiscoverArticleDto
import com.mynewsyapp.features_components.discover.data.local.models.DiscoverKeys
import com.mynewsyapp.features_components.favourite.data.dao.FavouriteDao
import com.mynewsyapp.features_components.headline.data.local.dao.HeadlineDao
import com.mynewsyapp.features_components.headline.data.local.dao.HeadlineRemoteKeyDao
import com.mynewsyapp.features_components.headline.data.local.model.HeadlineDto
import com.mynewsyapp.features_components.headline.data.local.model.HeadlineRemoteKey
import com.mynewsyapp.features_components.search.data.local.dao.SearchArticleDao
import com.mynewsyapp.features_components.search.data.local.dao.SearchRemoteKeyDao
import com.mynewsyapp.features_components.search.data.local.models.SearchDto
import com.mynewsyapp.features_components.search.data.local.models.SearchRemoteKey

@Database(
    entities = [
        HeadlineDto::class,
        HeadlineRemoteKey::class,
        DiscoverArticleDto::class,
        DiscoverKeys::class,
        SearchDto::class,
        SearchRemoteKey::class,
        SettingsDto::class
    ],
    exportSchema = false,
    version = 1
)
abstract class NewsyArticleDatabase : RoomDatabase() {
    abstract fun headlineDao(): HeadlineDao
    abstract fun headlineRemoteDao(): HeadlineRemoteKeyDao
    abstract fun discoverArticleDao(): DiscoverArticleDao
    abstract fun discoverRemoteKeyDao(): DiscoverRemoteKeyDao
    abstract fun detailDao(): DetailDao
    abstract fun searchArticleDao(): SearchArticleDao
    abstract fun searchKeyDao(): SearchRemoteKeyDao
    abstract fun favouriteDao(): FavouriteDao
    abstract fun settingDao(): SettingDao
}