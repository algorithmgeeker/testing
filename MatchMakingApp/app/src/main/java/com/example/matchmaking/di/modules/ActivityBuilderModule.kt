package com.example.matchmaking.di.modules

import com.example.matchmaking.di.annotations.ActivityScope
import com.example.matchmaking.features.matchmaking.di.MatchMakingModule
import com.example.matchmaking.features.matchmaking.ui.MatchMakingActivity
import com.example.matchmaking.features.matchmaking.di.MatchMakingViewModelModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {
    @ContributesAndroidInjector(modules = [MatchMakingViewModelModule::class, MatchMakingModule::class])
    @ActivityScope
    abstract fun contributeMatchMakingActivity(): MatchMakingActivity
}