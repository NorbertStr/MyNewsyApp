package com.mynewsyapp.features_components.headline.data.local.dao

import androidx.room.Room
import com.google.common.truth.Truth.assertThat
import com.mynewsyapp.MainDispatcherRule
import com.mynewsyapp.features_components.core.data.local.NewsyArticleDatabase
import com.mynewsyapp.utils.Utils
import com.mynewsyapp.utils.getTestData
import kotlinx.coroutines.flow.first

import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class HeadlineDaoKtTest {

    private lateinit var headlineDao: HeadlineDao
    private lateinit var db: NewsyArticleDatabase

    @get:Rule
    val mainCoroutineDispatcher = MainDispatcherRule()

    @Before
    fun setUp() {
        val app = RuntimeEnvironment.getApplication()
        db = Room.inMemoryDatabaseBuilder(
            context = app.applicationContext,
            klass = NewsyArticleDatabase::class.java
        ).allowMainThreadQueries().build()
        headlineDao = db.headlineDao()
    }

    @After
    fun tearDown() {
        db.close()
    }


    @Test
    fun `getAllArticles return all articles from db correctly`() = runTest {

        //GIVEN
        val expected = Utils.headlineDto[0]
        headlineDao.insertHeadlineArticle(listOf(expected))
        //WHEN
        val actual = headlineDao.getAllHeadlineArticles().getTestData()
        //THEN
        assertThat(actual[0]).isEqualTo(expected.copy(id = 1))

    }

    @Test
    fun `getHeadlineArticle return article from db correctly`() = runTest {

        //GIVEN
        val expected = Utils.headlineDto
        headlineDao.insertHeadlineArticle(expected)

        //WHEN
        val actual = headlineDao.getHeadlineArticle(1).first()

        //THEN
        assertThat(actual).isEqualTo(expected[0].copy(1))

            assertThat(actual.author).isEqualTo(expected[0].author)
            assertThat(actual.content).isEqualTo(expected[0].content)
            assertThat(actual.description).isEqualTo(expected[0].description)
            assertThat(actual.publishedAt).isEqualTo(expected[0].publishedAt)
            assertThat(actual.id).isEqualTo(1)

    }

    @Test
    fun `deleteAllArticle remove all non favorite article in db`() = runTest {
        //GIVEN
        val favouriteArticle = Utils.headlineDto[0].copy(favourite = true)
        val expected = Utils.headlineDto
        headlineDao.insertHeadlineArticle(listOf(favouriteArticle))
        headlineDao.insertHeadlineArticle(expected)
        //WHEN
        headlineDao.removeAllHeadlineArticles()
        //THEN
        val actual = headlineDao.getAllHeadlineArticles().getTestData()
        assertThat(actual.size).isEqualTo(1)
    }

//    @Test
//    fun `removeFavouriteArticle deletes favourite article from db`() = runTest {
//        //Given
//        val headlineArticle = Utils.headlineDto
//        val headlineArticleFav = Utils.headlineDto[0].copy(favourite = true)
//        headlineDao.insertHeadlineArticle(listOf(headlineArticleFav))
//        headlineDao.insertHeadlineArticle(headlineArticle)
//        //When
//        headlineDao.removeFavouriteArticle(1)
//        headlineDao.removeFavouriteArticle(2)
//        //Then
//        val actual = headlineDao.getHeadlineArticle(1).first()
//        val actual2 = headlineDao.getHeadlineArticle(2).first()
//        assertThat(actual).isNull()
//        assertThat(actual2).isNotNull()
//    }

    @Test
    fun `updateFavouriteArticle updates favourite status from true to false and from false to true`() = runTest {
        //GIVEN
        val favArticle = Utils.headlineDto[0].copy(favourite = true)
        val notFavArticle = Utils.headlineDto[1].copy(favourite = false)
        headlineDao.insertHeadlineArticle(listOf(favArticle,notFavArticle))

        //When
        headlineDao.updateFavouriteArticle(false,1)
        headlineDao.updateFavouriteArticle(true,2)
        //Then
        val actual1 = headlineDao.getHeadlineArticle(1).first()
        val actual2 = headlineDao.getHeadlineArticle(2).first()
        assertThat(actual1.favourite).isFalse()
        assertThat(actual2.favourite).isTrue()

    }
}