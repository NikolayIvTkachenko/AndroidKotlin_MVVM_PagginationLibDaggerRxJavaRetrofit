package com.rsh_engineering.tkachenkoni.gitviewmanager.di.modules

import com.rsh_engineering.tkachenkoni.gitviewmanager.App
import com.rsh_engineering.tkachenkoni.gitviewmanager.data.api.NetworkApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 *
 * Created by Nikolay Tkachenko on 22, October, 2020
 *
 */
@Module
class AppModule(var app: App){

    @Provides
    @Singleton
    fun provideApp(): App? = app



}