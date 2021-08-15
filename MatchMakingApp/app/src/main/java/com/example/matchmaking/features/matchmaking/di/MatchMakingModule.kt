package com.example.matchmaking.features.matchmaking.di

import android.app.Application
import com.example.matchmaking.database.MatchMakingDatabase
import com.example.matchmaking.di.annotations.ActivityScope
import com.example.matchmaking.features.matchmaking.ui.repository.MatchMakingRepository
import com.example.matchmaking.features.matchmaking.utils.MatchingConverter
import com.example.matchmaking.network.MatchMakingApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MatchMakingModule {
    @Provides
    @ActivityScope
    fun providesMatchMakingApi(retrofit: Retrofit): MatchMakingApi {
        return retrofit.create(MatchMakingApi::class.java)
    }

    @Provides
    @ActivityScope
    fun providesMatchingConverter(): MatchingConverter {
        return MatchingConverter()
    }

    @Provides
    @ActivityScope
    fun providesMatchMakingRepository(makingApi: MatchMakingApi, database: MatchMakingDatabase, converter: MatchingConverter, application: Application): MatchMakingRepository {
        return MatchMakingRepository(makingApi, database, converter, application)
    }
}