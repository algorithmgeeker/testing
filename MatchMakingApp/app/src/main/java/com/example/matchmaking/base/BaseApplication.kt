package com.example.matchmaking.base

import com.example.matchmaking.di.components.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class BaseApplication : DaggerApplication() {

    override fun applicationInjector() : AndroidInjector<out DaggerApplication?> {
        return DaggerAppComponent.builder()
            .application(this)
            .build()
    }
}