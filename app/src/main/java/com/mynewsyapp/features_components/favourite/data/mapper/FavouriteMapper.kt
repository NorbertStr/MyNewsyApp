package com.mynewsyapp.features_components.favourite.data.mapper

class FavouriteMapper:Mapper<FavouriteDto,FavouriteArticle> {
    override fun toModel(value: FavouriteDto): FavouriteArticle {
        return value.run {
            FavouriteArticle(
                id = id,
                author = author,
                content = content,
                description = description,
                publishedAt = publishedAt,
                source = source,
                title = title,
                url = url,
                urlToImage = urlToImage,
                favourite = favourite,
                category = category,
                page = page
            )
        }
    }

    override fun fromModel(value: FavouriteArticle): FavouriteDto {
        return  value.run {
            FavouriteDto(
                id = id,
                author = author,
                content = content,
                description = description,
                publishedAt = publishedAt,
                source = source,
                title = title,
                url = url,
                urlToImage = urlToImage,
                favourite = favourite,
                category = category,
                page = page
            )
        }
    }
}