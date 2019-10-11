package com.android.culqi.culqi_android

import android.app.Application
import com.android.culqi.culqi_android.di.modules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CulqiApplication : Application() {


    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@CulqiApplication)
            androidLogger()
            modules(modules)
        }
    }
}