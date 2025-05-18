package com.mynewsyapp.features_components.headline.data.local.dao

import androidx.room.Room
import com.mynewsyapp.MainDispatcherRule
import com.mynewsyapp.features_components.core.data.local.NewsyArticleDatabase
import org.junit.After
import org.junit.Before
import org.junit.Rule

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
    fun tearDown(){
        db.close()
    }
}