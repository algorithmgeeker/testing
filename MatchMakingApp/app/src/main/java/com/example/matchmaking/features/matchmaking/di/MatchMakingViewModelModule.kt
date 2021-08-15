package com.example.matchmaking.features.matchmaking.di

import androidx.lifecycle.ViewModel
import com.example.matchmaking.di.annotations.ActivityScope
import com.example.matchmaking.features.matchmaking.ui.viewmodel.MatchMakingViewModel
import com.example.matchmaking.di.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MatchMakingViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MatchMakingViewModel::class)
    @ActivityScope
    abstract fun bindMatchMakingViewModel(mapMatchMakingViewModel : MatchMakingViewModel): ViewModel
}