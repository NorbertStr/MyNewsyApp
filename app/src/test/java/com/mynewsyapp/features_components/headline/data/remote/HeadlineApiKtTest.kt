package com.mynewsyapp.features_components.headline.data.remote

import com.google.common.truth.Truth.assertThat
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.mynewsyapp.MainDispatcherRule
import com.mynewsyapp.utils.MockResponseFileReader
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.IOException
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Retrofit


class HeadlineApiKtTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var api: HeadlineApi
    private val json = Json {
        coerceInputValues = true
        ignoreUnknownKeys = true
    }
    private val contentType = "application/json".toMediaType()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val succesJsonFileName = "get_article_success.json"

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
        api = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
            .create(HeadlineApi::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `getHeadlines should hot correct endpoint`() = runTest {
        //GIVEN
        mockWebServer.enqueue(MockResponse().setBody("{}").setResponseCode(200))
        val expectedEndPoint =
            "/v2/top-headlines?apiKey=API_KEY&category=sports&country=us&language=en&page=0&pageSize=20"
        //WHEN
        api.getHeadlines(
            key = "API_KEY",
            category = "sports",
            country = "us",
            language = "en",
            pageSize = 20,
            page = 0
        )
        //THEN
        val request = mockWebServer.takeRequest()
        assertThat(request.path).isEqualTo(expectedEndPoint)
    }

    @Test
    fun `getHeadline should return correct data`() = runTest {
        //Given
        val successJson = MockResponseFileReader(succesJsonFileName).content
        mockWebServer.enqueue(MockResponse().setBody(successJson).setResponseCode(200))

        //When
        val actualResponse = api.getHeadlines(
            key = "API_KEY",
            category = "sports",
            country = "us",
            language = "en",
            pageSize = 20,
            page = 0
        )

        //Then
        assertThat(actualResponse).isNotNull()
        assertThat(actualResponse.totalResults).isEqualTo(61)
        assertThat(actualResponse.status).isEqualTo("ok")
        assertThat(actualResponse.articles[0].author).isEqualTo("CNN")
    }

    @Test(expected = HttpException::class)
    fun `getHeadline article handles 404 error`() = runTest{
        //GIVEN
        mockWebServer.enqueue(MockResponse().setResponseCode(404))
        //WHEN
        val actualResponse = api.getHeadlines(
            key = "API_KEY",
            category = "sports",
            country = "us",
            language = "en",
            pageSize = 20,
            page = 0
        )
    }

    @Test(expected = IOException::class)
    fun `getHeadline article handles network failure`() = runTest{
        //GIVEN
        mockWebServer.shutdown()
        //WHEN
        val actualResponse = api.getHeadlines(
            key = "API_KEY",
            category = "sports",
            country = "us",
            language = "en",
            pageSize = 20,
            page = 0
        )
    }
}