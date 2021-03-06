package com.rsh_engineering.tkachenkoni.gitviewmanager.di.modules

import com.rsh_engineering.tkachenkoni.gitviewmanager.data.api.NetworkApi
import com.rsh_engineering.tkachenkoni.gitviewmanager.data.repository_impl.GitNetworkDetailRepositoryImpl
import com.rsh_engineering.tkachenkoni.gitviewmanager.data.repository_impl.GitNetworkRepositoryImpl
import com.rsh_engineering.tkachenkoni.gitviewmanager.domain.repository.GitNetworkDetailRepository
import com.rsh_engineering.tkachenkoni.gitviewmanager.domain.repository.GitNetworkRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 *
 * Created by Nikolay Tkachenko on 22, October, 2020
 *
 */

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideGitNetworkRepository(networkApi : NetworkApi): GitNetworkRepository {
        return GitNetworkRepositoryImpl(networkApi)
    }

    @Provides
    @Singleton
    fun provideGitNetworkDetailRepository(networkApi : NetworkApi): GitNetworkDetailRepository {
        return GitNetworkDetailRepositoryImpl(networkApi)
    }

}