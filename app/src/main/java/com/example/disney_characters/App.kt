package com.example.disney_characters

import android.app.Application
import com.example.disney_characters.di.AppComponent
import com.example.disney_characters.di.DaggerAppComponent

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    private fun initDagger() {
        appComponent = DaggerAppComponent.builder().provideContext(applicationContext).build()
    }

    companion object {

        var appComponent: AppComponent? = null
    }
}