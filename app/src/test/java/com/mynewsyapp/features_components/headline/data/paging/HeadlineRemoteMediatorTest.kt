package com.mynewsyapp.features_components.headline.data.paging

import androidx.room.Room
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.mynewsyapp.MainDispatcherRule
import com.mynewsyapp.features_components.core.data.local.NewsyArticleDatabase
import com.mynewsyapp.features_components.headline.data.remote.HeadlineApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import retrofit2.Retrofit



@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class HeadlineRemoteMediatorTest {

 private lateinit var db: NewsyArticleDatabase

 @get:Rule
 val mainCoroutineRule = MainDispatcherRule()

 private lateinit var api: HeadlineApi
 private lateinit var mockWebServer: MockWebServer
 private val json = Json{
  coerceInputValues = true
  ignoreUnknownKeys = true
 }

 private val contentType = "application/json".toMediaType()

 @Before
 fun setUp(){
  val app = RuntimeEnvironment.getApplication()
  db = Room.inMemoryDatabaseBuilder(
   context = app.applicationContext,
   klass = NewsyArticleDatabase::class.java
  ).allowMainThreadQueries().build()

  mockWebServer = MockWebServer()
  mockWebServer.start()
  api = Retrofit.Builder()
   .baseUrl(mockWebServer.url("/"))
   .addConverterFactory(json.asConverterFactory(contentType))
   .build()
   .create(HeadlineApi::class.java)
 }

 @After
 fun tearDown(){
  db.close()
  mockWebServer.shutdown()
 }

}