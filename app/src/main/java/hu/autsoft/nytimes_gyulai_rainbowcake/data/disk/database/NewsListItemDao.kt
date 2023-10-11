package hu.autsoft.nytimes_gyulai_rainbowcake.data.disk.database

import androidx.room.*
import hu.autsoft.nytimes_gyulai_rainbowcake.data.disk.model.NewsListItem

@Dao
interface NewsListItemDao {
    @Query("SELECT * FROM newslistitem")
    suspend fun getAll(): List<NewsListItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(newsListItems: List<NewsListItem>)

    @Query("DELETE FROM newslistitem")
    suspend fun deleteAll()
}