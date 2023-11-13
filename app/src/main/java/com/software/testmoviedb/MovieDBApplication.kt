package com.software.testmoviedb

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MovieDBApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}