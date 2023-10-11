package hu.autsoft.nytimes_gyulai_rainbowcake.data.disk.database

import androidx.room.Database
import androidx.room.RoomDatabase
import hu.autsoft.nytimes_gyulai_rainbowcake.data.disk.model.NewsListItem

@Database(entities = [NewsListItem::class], version = 1)
abstract class NewsListDatabase : RoomDatabase() {

    abstract fun newslistItemDao(): NewsListItemDao
}