package com.rsh_engineering.tkachenkoni.gitviewmanager.presentation.viewmodels

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

/**
 *
 * Created by Nikolay Tkachenko on 22, October, 2020
 *
 */
open class BaseViewModel  : ViewModel() {
    val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}