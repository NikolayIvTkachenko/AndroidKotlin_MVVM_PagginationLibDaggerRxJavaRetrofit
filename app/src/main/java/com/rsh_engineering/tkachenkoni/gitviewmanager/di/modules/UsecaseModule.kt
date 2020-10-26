package com.rsh_engineering.tkachenkoni.gitviewmanager.di.modules

import com.rsh_engineering.tkachenkoni.gitviewmanager.domain.repository.GitNetworkRepository
import com.rsh_engineering.tkachenkoni.gitviewmanager.domain.repository.ItemResponsePageListRepository
import com.rsh_engineering.tkachenkoni.gitviewmanager.domain.usecases.NetworkUseCase
import com.rsh_engineering.tkachenkoni.gitviewmanager.presentation.viewmodels.DetailViewModel
import com.rsh_engineering.tkachenkoni.gitviewmanager.presentation.viewmodels.SearcViewModel
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
    fun provideGeneralUseCase(
        repository : GitNetworkRepository,
        repositoryPagination : ItemResponsePageListRepository)
            = NetworkUseCase(repository, repositoryPagination)

}