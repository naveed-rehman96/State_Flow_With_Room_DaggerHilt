package com.navdroid.kotlinFlowSample

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplicationClass : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}
