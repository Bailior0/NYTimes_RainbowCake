package hu.autsoft.nytimes_gyulai_rainbowcake

import android.app.Application
import co.zsmb.rainbowcake.config.rainbowCake
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
open class NyTimesApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        rainbowCake {
            isDebug = BuildConfig.DEBUG
        }
    }
}