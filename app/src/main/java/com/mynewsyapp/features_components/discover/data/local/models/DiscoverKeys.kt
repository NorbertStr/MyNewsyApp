package com.mynewsyapp.features_components.discover.data.local.models

@Entity(tableName = "discover_keys")
data class DiscoverKeys(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo("article_id")
    val articleId: String,
    val prevKey: Int?,
    val currentPage: Int?,
    val nextKey: Int?,
    @ColumnInfo("current_category")
    val currentCategory: String,
    @ColumnInfo("created_at")
    val createdAt: Long = System.currentTimeMillis(),
)
