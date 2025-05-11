package com.mynewsyapp.features_components.core.data.remote.models


import com.mynewsyapp.features_components.discover.data.local.models.DiscoverArticleDto
import com.mynewsyapp.features_components.headline.data.local.model.HeadlineDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Article(
    @SerialName("author")
    val author: String = "",
    @SerialName("content")
    val content: String? = null,
    @SerialName("description")
    val description: String? = null,
    @SerialName("publishedAt")
    val publishedAt: String = "",
    @SerialName("source")
    val source: Source = Source(),
    @SerialName("title")
    val title: String = "",
    @SerialName("url")
    val url: String = "",
    @SerialName("urlToImage")
    val urlToImage: String? = null,
)

fun Article.toDiscoverArticle(page: Int, category: String): DiscoverArticleDto {
    return DiscoverArticleDto(
        author = author,
        content = content ?: "empty value",
        description = description ?: " empty value",
        publishedAt = publishedAt,
        title = title,
        source = source.name,
        category = category,
        url = url,
        urlToImage = urlToImage,
        page = page
    )
}

fun Article.toHeadlineArticle(page: Int, category: String): HeadlineDto {

    if(category.isEmpty()) throw  IllegalArgumentException("category can not be empty")
    if (page < 0 ) throw IndexOutOfBoundsException("page accepts only positive values, but $page was passed")


    return HeadlineDto(
        author = formatEmptyValue(author, "author"),
        content = formatEmptyValue(content, "content"),
        description = formatEmptyValue(description, "description"),
        publishedAt = formatEmptyValue(publishedAt, "date"),
        source = formatEmptyValue(source.name, "source"),
        title = formatEmptyValue(title, "title"),
        url = url,
        urlToImage = urlToImage,
        page = page,
        category = category
    )
}


private fun formatEmptyValue(value: String?, default: String = ""): String {
    if (value.isNullOrEmpty()) return "Unknown $default"
    return value
}








