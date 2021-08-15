package com.example.matchmaking.di.modules

import androidx.lifecycle.ViewModelProvider
import com.example.matchmaking.di.viewmodel.ViewModelProviderFactory
import dagger.Binds
import dagger.Module


@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelProviderFactory): ViewModelProvider.Factory
}