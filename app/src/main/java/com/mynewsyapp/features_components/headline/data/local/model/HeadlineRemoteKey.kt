package com.mynewsyapp.features_components.headline.data.local.model

@Entity("headline_key")
data class HeadlineRemoteKey(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "article_id")
    val articleId: String,
    val prevKey: Int?,
    val currentPage: Int?,
    val nextKey: Int?,
    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis(),
)
