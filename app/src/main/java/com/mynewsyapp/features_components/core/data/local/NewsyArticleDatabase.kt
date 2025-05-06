package com.mynewsyapp.features_components.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

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