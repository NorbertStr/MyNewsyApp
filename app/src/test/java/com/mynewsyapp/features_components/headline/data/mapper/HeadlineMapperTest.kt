package com.mynewsyapp.features_components.headline.data.mapper

import com.google.common.truth.Truth.assertThat
import com.mynewsyapp.features_components.core.domain.mapper.Mapper
import com.mynewsyapp.features_components.core.domain.models.NewsyArticle
import com.mynewsyapp.features_components.headline.data.local.model.HeadlineDto


import org.junit.Before
import org.junit.Test

class HeadlineMapperTest {

    lateinit var headlineMapper: Mapper<HeadlineDto, NewsyArticle>

    @Before
    fun setUp() {
        headlineMapper = HeadlineMapper()
    }

    @Test
    fun `fromModel should map to HeadlineDto correctly`() {

        //Given
        val newsyArticle = NewsyArticle(
            id = 0,
            author = "author",
            content = "content",
            description = "descriptio",
            publishedAt = "publishedAt",
            source = "source",
            title = "title",
            url = "url",
            urlToImage = "urlToImage",
            favourite = false,
            category = "sports",
            page = 0
        )

        //When
        val headlineDto = headlineMapper.fromModel(newsyArticle)

        //Then
         assertThat(headlineDto.author).isEqualTo(newsyArticle.author)
         assertThat(headlineDto.content).isEqualTo(newsyArticle.content)
         assertThat(headlineDto.id).isEqualTo(0)
         assertThat(headlineDto.description).isEqualTo(newsyArticle.description)
         assertThat(headlineDto.publishedAt).isEqualTo(newsyArticle.publishedAt)
         assertThat(headlineDto.source).isEqualTo(newsyArticle.source)
         assertThat(headlineDto.title).isEqualTo(newsyArticle.title)
         assertThat(headlineDto.url).isEqualTo(newsyArticle.url)
         assertThat(headlineDto.urlToImage).isEqualTo(newsyArticle.urlToImage)
         assertThat(headlineDto.favourite).isEqualTo(newsyArticle.favourite)
         assertThat(headlineDto.category).isEqualTo("sports")
         assertThat(headlineDto.page).isEqualTo(0)
    }

    @Test
    fun `toModel should map to HeadlineDto correctly`(){

        //Given
        val headlineDto = HeadlineDto(
            id = 0,
            author = "author",
            content = "content",
            description = "description",
            publishedAt = "publishedAt",
            source = "source",
            title = "title",
            url = "url",
            urlToImage = "urlToImage",
            favourite = false,
            category = "sport",
            page = 1
        )

        //When
        val articleDto = headlineMapper.toModel(headlineDto)

        //Then
        assertThat(articleDto.id).isEqualTo(0)
        assertThat(articleDto.author).isEqualTo(headlineDto.author)
        assertThat(articleDto.content).isEqualTo(headlineDto.content)
        assertThat(articleDto.description).isEqualTo(headlineDto.description)
        assertThat(articleDto.publishedAt).isEqualTo(headlineDto.publishedAt)
        assertThat(articleDto.source).isEqualTo(headlineDto.source)
        assertThat(articleDto.title).isEqualTo(headlineDto.title)
        assertThat(articleDto.url).isEqualTo(headlineDto.url)
        assertThat(articleDto.urlToImage).isEqualTo(headlineDto.urlToImage)
        assertThat(articleDto.favourite).isEqualTo(false)
        assertThat(articleDto.category).isEqualTo("sport")
        assertThat(articleDto.page).isEqualTo(1)
    }
}