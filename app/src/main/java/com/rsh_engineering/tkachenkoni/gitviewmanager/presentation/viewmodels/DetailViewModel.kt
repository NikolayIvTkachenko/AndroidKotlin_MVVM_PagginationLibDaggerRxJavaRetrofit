package com.rsh_engineering.tkachenkoni.gitviewmanager.presentation.viewmodels

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProviders
import com.rsh_engineering.tkachenkoni.gitviewmanager.App
import com.rsh_engineering.tkachenkoni.gitviewmanager.R
import com.rsh_engineering.tkachenkoni.gitviewmanager.domain.model_entity.ItemResponse
import com.rsh_engineering.tkachenkoni.gitviewmanager.domain.usecases.DetailNetworkUseCase
import com.rsh_engineering.tkachenkoni.gitviewmanager.domain.usecases.NetworkUseCase
import javax.inject.Inject

/**
 *
 * Created by Nikolay Tkachenko on 22, October, 2020
 *
 */
class DetailViewModel @Inject constructor(val detailNetworkUseCase: DetailNetworkUseCase)  : BaseViewModel(){

    private val lvDataLanguages = MutableLiveData<Map<String, Int>>()
    private val lvReadme = MutableLiveData<String>()
    private val lvItemResponse = MutableLiveData<ItemResponse>()

    fun setLvItemResponse(value : ItemResponse){
        lvItemResponse.value = value
    }

    fun getLanguages(){
        val owner = lvItemResponse.value?.owner?.login
        val name = lvItemResponse.value?.name

        compositeDisposable.add(detailNetworkUseCase.listLanguages(owner!!, name!!)
            .subscribe({
                    languages ->
                lvDataLanguages.value = languages
            }, {}))
    }

    fun getReadMe(){
        val owner = lvItemResponse.value?.owner?.login
        val name = lvItemResponse.value?.name
        val add = compositeDisposable.add(detailNetworkUseCase.getReadme(owner!!, name!!)
            .subscribe({readme ->
                lvReadme.value = readme.string()
            }, {error ->
                Log.d("TESTNETWORK", "readsme error = $error")
            })
        )
    }

    fun getLvReadMe():MutableLiveData<String> {
        return lvReadme
    }

    fun getLvLanguages():MutableLiveData<Map<String, Int>>{
        return lvDataLanguages
    }
}