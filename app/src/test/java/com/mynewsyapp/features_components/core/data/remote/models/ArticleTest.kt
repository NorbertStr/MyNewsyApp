package com.mynewsyapp.features_components.core.data.remote.models

import com.google.common.truth.Truth.assertThat
import com.mynewsyapp.utils.Utils
import org.junit.Test

class ArticleTest {

    @Test
    fun `toHeadlineArticle should map to headline correctly`() {

        //GIVEN
        val article = Utils.testArticles[0]

        //WHEN
        val headlineDto = article.toHeadlineArticle(0, "sports")

        //THEN
        assertThat(headlineDto.author).isEqualTo(article.author)
        assertThat(headlineDto.description).isEqualTo(article.description)
        assertThat(headlineDto.content).isEqualTo(article.content)
        assertThat(headlineDto.source).isEqualTo(article.source.name)
        assertThat(headlineDto.title).isEqualTo(article.title)
        assertThat(headlineDto.category).isEqualTo("sports")
        assertThat(headlineDto.favourite).isFalse()
        assertThat(headlineDto.publishedAt).isEqualTo(article.publishedAt)
        assertThat(headlineDto.url).isEqualTo(article.url)
        assertThat(headlineDto.urlToImage).isEqualTo(article.urlToImage)
        assertThat(headlineDto.id).isEqualTo(0)
        assertThat(headlineDto.page).isEqualTo(0)

    }

    @Test
    fun `toHeadlineArticle should map null or empty value with formated output`(){
        //GIVEN
        val articles = Article()

        //WHEN
        val headlineDto = articles.toHeadlineArticle(0, "sports")

        //THEN
        assertThat(headlineDto.author).isEqualTo("Unknown author")
        assertThat(headlineDto.description).isEqualTo("Unknown description")
        assertThat(headlineDto.content).isEqualTo("Unknown content")
        assertThat(headlineDto.source).isEqualTo("Unknown source")
        assertThat(headlineDto.title).isEqualTo("Unknown title")
        assertThat(headlineDto.publishedAt).isEqualTo("Unknown date")
        assertThat(headlineDto.url).isEmpty()
        assertThat(headlineDto.urlToImage).isNull()
    }

}