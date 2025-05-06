package com.mynewsyapp.features_components.discover.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DiscoverArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllArticles(list: List<DiscoverArticleDto>)

    @Query("SELECT * FROM discover_article WHERE category=:category")
    fun getDiscoverArticleDataSource(category: String): PagingSource<Int, DiscoverArticleDto>

    @Query("SELECT * FROM discover_article WHERE id=:id")
    fun getDiscoverArticle(id: Int): Flow<DiscoverArticleDto>

    @Query("DELETE FROM discover_article WHERE favourite= 0 AND category =:category ")
    suspend fun removeAllDiscoverArticles(category: String)

    @Delete
    suspend fun removeFavouriteArticle(discoverArticleDto: DiscoverArticleDto)

    @Query(
        "UPDATE discover_article SET favourite=:isFavourite WHERE id=:id"
    )
    suspend fun updateFavouriteArticle(isFavourite: Boolean, id: Int): Int
}