package com.rsh_engineering.tkachenkoni.gitviewmanager.di.modules

import androidx.lifecycle.ViewModel
import com.rsh_engineering.tkachenkoni.gitviewmanager.presentation.viewmodels.DetailViewModel
import com.rsh_engineering.tkachenkoni.gitviewmanager.presentation.viewmodels.GeneralViewModel
import com.rsh_engineering.tkachenkoni.gitviewmanager.presentation.viewmodels.SearcViewModel
import com.rsh_engineering.tkachenkoni.gitviewmanager.presentation.viewmodels.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 *
 * Created by Nikolay Tkachenko on 22, October, 2020
 *
 */

@Module
abstract class ViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(GeneralViewModel::class)
    internal abstract fun bindGeneralViewModel(viewModel: GeneralViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    internal abstract fun bindDetailViewModel(viewModel: DetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearcViewModel::class)
    internal abstract fun bindSearcViewModel(viewModel: SearcViewModel): ViewModel

}