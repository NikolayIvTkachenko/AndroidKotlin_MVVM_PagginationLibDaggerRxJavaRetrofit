package com.rsh_engineering.tkachenkoni.gitviewmanager.di.modules

import androidx.lifecycle.ViewModelProvider
import com.rsh_engineering.tkachenkoni.gitviewmanager.presentation.viewmodels.ViewModelFactory
import dagger.Binds
import dagger.Module

/**
 *
 * Created by Nikolay Tkachenko on 22, October, 2020
 *
 */
@Module
abstract class ViewModelFactoryModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}