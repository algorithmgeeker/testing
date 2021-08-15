package com.example.matchmaking.di.modules

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.os.Message
import com.example.matchmaking.base.SingleLiveEvent
import com.example.matchmaking.database.MatchMakingDatabase
import com.example.matchmaking.di.annotations.AppScope
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {
    @Provides
    @AppScope
    fun provideSingleLiveEvent(): SingleLiveEvent<Message> {
        return SingleLiveEvent()
    }

    @Provides
    @AppScope
    fun provideSharedPreference(context: Application): SharedPreferences {
        return context.getSharedPreferences("com.example.matchmakingapp.shared", Context.MODE_PRIVATE)
    }

    @AppScope
    @Provides
    fun providePicasso(application: Application): Picasso {
        return Picasso.Builder(application)
            .build()
    }

    @AppScope
    @Provides
    fun provideDatabase(application: Application): MatchMakingDatabase {
        return MatchMakingDatabase.getDatabase(application)
    }
}