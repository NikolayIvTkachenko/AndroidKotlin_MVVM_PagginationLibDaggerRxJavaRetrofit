package com.rsh_engineering.tkachenkoni.gitviewmanager.di.modules

import com.rsh_engineering.tkachenkoni.gitviewmanager.domain.repository.GitNetworkDetailRepository
import com.rsh_engineering.tkachenkoni.gitviewmanager.domain.usecases.DetailNetworkUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 *
 * Created by Nikolay Tkachenko on 22, October, 2020
 *
 */

@Module
class UsecaseModule {

    @Singleton
    @Provides
    fun provideDetailNetworkUseCase(
        repository : GitNetworkDetailRepository)
            = DetailNetworkUseCase(repository)

}