package hu.autsoft.nytimes_gyulai_rainbowcake.data.network

import hu.autsoft.nytimes_gyulai_rainbowcake.data.disk.model.NewsData
import retrofit2.http.GET

interface NewsApi {
    @GET("/svc/mostpopular/v2/viewed/1.json")
    suspend fun getNews(): NewsData?
}