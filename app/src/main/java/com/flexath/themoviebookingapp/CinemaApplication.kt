package com.flexath.themoviebookingapp

import android.app.Application
import com.flexath.themoviebookingapp.data.model.CinemaModelImpl

class CinemaApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        CinemaModelImpl.initCinemaDatabase(applicationContext)
    }
}