package com.example.basicnavigation

import android.app.Application
import com.example.basicnavigation.database.DatabaseManager

open class MyApplication: Application() {
    override fun onCreate() {
        DatabaseManager.intance.initializeDb(applicationContext)
        super.onCreate()
    }
}