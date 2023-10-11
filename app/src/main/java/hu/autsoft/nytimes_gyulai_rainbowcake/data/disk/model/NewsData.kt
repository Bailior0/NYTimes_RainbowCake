package hu.autsoft.nytimes_gyulai_rainbowcake.data.disk.model

import com.google.gson.annotations.SerializedName

data class NewsData (
    val results: List<NewsResults>
)

data class NewsResults (
    val id: Long,
    val url: String,
    var title: String? = null,
    var byline: String? = null,
    @SerializedName("published_date")
    var publishedDate: String? = null,
    val media: List<NewsMedia>? = null
)

data class NewsMedia (
    @SerializedName("media-metadata")
    val mediaMetadata: List<NewsMediaMetadata>? = null
)

data class NewsMediaMetadata (
    val url: String? = null
)