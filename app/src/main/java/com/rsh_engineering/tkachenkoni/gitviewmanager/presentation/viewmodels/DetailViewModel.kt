package com.rsh_engineering.tkachenkoni.gitviewmanager.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.rsh_engineering.tkachenkoni.gitviewmanager.domain.model_entity.ItemResponse
import com.rsh_engineering.tkachenkoni.gitviewmanager.domain.usecases.DetailNetworkUseCase
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