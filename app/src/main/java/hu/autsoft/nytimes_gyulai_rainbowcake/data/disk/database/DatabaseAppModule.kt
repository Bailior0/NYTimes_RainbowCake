package hu.autsoft.nytimes_gyulai_rainbowcake.data.disk.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseAppModule {

    @Singleton
    @Provides
    fun provideNewsDatabase( @ApplicationContext app: Context) = Room.databaseBuilder(
        app,
        NewsListDatabase::class.java,
        "newslist-list"
    ).build()

    @Singleton
    @Provides
    fun provideYourDao(db: NewsListDatabase) = db.newslistItemDao()
}