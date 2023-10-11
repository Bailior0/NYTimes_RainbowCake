package hu.autsoft.nytimes_gyulai_rainbowcake.domain

import hu.autsoft.nytimes_gyulai_rainbowcake.data.disk.database.NewsListItemDao
import hu.autsoft.nytimes_gyulai_rainbowcake.data.disk.model.NewsData
import hu.autsoft.nytimes_gyulai_rainbowcake.data.disk.model.NewsListItem
import hu.autsoft.nytimes_gyulai_rainbowcake.data.network.NetworkRepo
import javax.inject.Inject

class NewsInteractor @Inject constructor( private val database: NewsListItemDao, private val network: NetworkRepo) {

    suspend fun load(): List<NewsListItem> {
        refresh()
        return database.getAll()
    }

    suspend fun refresh(): List<NewsListItem> {
        val newsData = network.getNews()
        val newsList = addNews(newsData)
        if(newsData != null) {
            database.deleteAll()
            database.insertAll(newsList)
        }
        return newsList
    }

    private fun addNews(newsData: NewsData?): MutableList<NewsListItem> {
        var iconUrl: String?
        val newsList = mutableListOf<NewsListItem>()
        if (newsData != null) {
            for(news in newsData.results) {
                if(!news.media.isNullOrEmpty())
                    iconUrl = news.media[0].mediaMetadata?.get(0)?.url
                else
                    iconUrl = null

                val newsItem = NewsListItem(null, news.id, news.url, news.title.toString(), news.byline.toString(), news.publishedDate.toString(), iconUrl)
                newsList.add(newsItem)
            }
        }
        return newsList
    }
}