package com.rsh_engineering.tkachenkoni.gitviewmanager

import android.app.Application
import com.rsh_engineering.tkachenkoni.gitviewmanager.di.component.AppComponent
import com.rsh_engineering.tkachenkoni.gitviewmanager.di.component.DaggerAppComponent
import com.rsh_engineering.tkachenkoni.gitviewmanager.di.modules.AppModule
import com.rsh_engineering.tkachenkoni.gitviewmanager.di.modules.NetworkModule
import com.rsh_engineering.tkachenkoni.gitviewmanager.di.modules.RepositoryModule
import com.rsh_engineering.tkachenkoni.gitviewmanager.di.modules.UsecaseModule

/**
 *
 * Created by Nikolay Tkachenko on 22, October, 2020
 *
 */
class App : Application() {

    companion object{
        private var appComponent: AppComponent? = null

        fun getAppComponent(): AppComponent {
            return appComponent!!
        }
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .repositoryModule(RepositoryModule())
            .networkModule(NetworkModule())
            .usecaseModule(UsecaseModule())
            .appModule(AppModule(this))
            .build()
    }
}