package hu.autsoft.nytimes_gyulai_rainbowcake.data.disk.model

import android.os.Parcelable
import androidx.room.*
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "newslistitem")
data class NewsListItem(
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) var id: Long? = null,
    @ColumnInfo(name = "newsId") var newsId: Long,
    @ColumnInfo(name = "Url") var url: String,
    @ColumnInfo(name = "Title") var title: String,
    @ColumnInfo(name = "Byline") var byline: String,
    @ColumnInfo(name = "PublishedDate") var publishedDate: String,
    @ColumnInfo(name = "IconUrl") var iconUrl: String?
) : Parcelable