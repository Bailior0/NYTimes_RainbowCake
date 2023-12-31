package hu.autsoft.nytimes_gyulai_rainbowcake.data.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.autsoft.nytimes_gyulai_rainbowcake.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkAppModule {
    private const val SERVICE_URL = "https://api.nytimes.com"

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor {
            val original = it.request()
            val httpUrl = original.url()
            val newHttpUrl = httpUrl.newBuilder().addQueryParameter("api-key", BuildConfig.APP_ID).build()
            val request = original.newBuilder().url(newHttpUrl).build()
            it.proceed(request)
        }
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(SERVICE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): NewsApi = retrofit.create(NewsApi::class.java)
}