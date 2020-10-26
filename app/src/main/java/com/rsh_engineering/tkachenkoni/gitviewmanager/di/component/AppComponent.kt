package com.rsh_engineering.tkachenkoni.gitviewmanager.di.component

import com.rsh_engineering.tkachenkoni.gitviewmanager.data.api.NetworkApi
import com.rsh_engineering.tkachenkoni.gitviewmanager.data.repository_impl.ItemResponseDataSourceFactory
import com.rsh_engineering.tkachenkoni.gitviewmanager.di.modules.*
import com.rsh_engineering.tkachenkoni.gitviewmanager.presentation.fragments.DetailRepoFragment
import com.rsh_engineering.tkachenkoni.gitviewmanager.presentation.fragments.SearchRepoFragment
import dagger.Component
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 *
 * Created by Nikolay Tkachenko on 22, October, 2020
 *
 */
@Singleton
@Component(modules = [
    AppModule::class,
    NetworkModule::class,
    UsecaseModule::class,
    RepositoryModule::class,
    ViewModelFactoryModule::class,
    ViewModelsModule::class
])

interface AppComponent {

    fun inject(value: DetailRepoFragment)
    fun inject(value: SearchRepoFragment)

    fun inject(value : ItemResponseDataSourceFactory)

}