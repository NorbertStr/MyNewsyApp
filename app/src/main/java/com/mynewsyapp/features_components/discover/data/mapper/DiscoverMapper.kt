package com.mynewsyapp.features_components.discover.data.mapper

import com.mynewsyapp.features_components.core.domain.mapper.Mapper
import com.mynewsyapp.features_components.core.domain.models.NewsyArticle
import com.mynewsyapp.features_components.discover.data.local.models.DiscoverArticleDto

class DiscoverMapper: Mapper<DiscoverArticleDto, NewsyArticle> {
    override fun toModel(value: DiscoverArticleDto): NewsyArticle {
        return value.run {
            NewsyArticle(
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

    override fun fromModel(value: NewsyArticle): DiscoverArticleDto {
        return value.run {
            DiscoverArticleDto(
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