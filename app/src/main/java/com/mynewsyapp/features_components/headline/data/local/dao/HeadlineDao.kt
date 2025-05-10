package com.mynewsyapp.features_components.headline.data.local.dao

@Dao
interface HeadlineDao {
    @Query("SELECT * FROM headline_table")
    fun getAllHeadlineArticles(): PagingSource<Int, HeadlineDto>

    @Query("SELECT * FROM headline_table WHERE id=:id")
    fun getHeadlineArticle(id: Int): Flow<HeadlineDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHeadlineArticle(articles: List<HeadlineDto>)

    @Query(
        "DELETE FROM headline_table WHERE favourite=0"
    )
    suspend fun removeAllHeadlineArticles()

    @Delete
    suspend fun removeFavouriteArticle(headlineDto: HeadlineDto)

    @Query(
        "UPDATE headline_table SET favourite =:isFavourite WHERE id=:id"
    )
    suspend fun updateFavouriteArticle(isFavourite: Boolean, id: Int)

}