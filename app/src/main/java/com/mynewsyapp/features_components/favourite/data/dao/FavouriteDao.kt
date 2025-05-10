package com.mynewsyapp.features_components.favourite.data.dao

@Dao
interface FavouriteDao {
    @Query(
        """
            SELECT * FROM discover_article WHERE favourite=1
            UNION
            SELECT * FROM headline_table WHERE favourite=1
            UNION
            SELECT * FROM search_table WHERE favourite=1
        """
    )
    fun getAllFavouriteArticles(): PagingSource<Int, FavouriteDto>
}