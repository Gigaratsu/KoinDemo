package com.thedomain.koindemonstration

import android.app.Application
import com.thedomain.koindemonstration.koin.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class KoinApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@KoinApp)
            modules(appModule)
        }

        DataManager.getInstance().setContext(this)
    }
}