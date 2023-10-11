package hu.autsoft.nytimes_gyulai_rainbowcake.data.network

import hu.autsoft.nytimes_gyulai_rainbowcake.data.disk.model.NewsData
import javax.inject.Inject

class NetworkRepo @Inject constructor(
    private val api: NewsApi
) {

    suspend fun getNews(): NewsData? {
        return api.getNews()
    }
}