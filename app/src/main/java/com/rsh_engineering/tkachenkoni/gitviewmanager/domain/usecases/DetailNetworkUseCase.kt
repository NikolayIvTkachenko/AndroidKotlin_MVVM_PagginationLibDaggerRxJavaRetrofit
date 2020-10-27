package com.rsh_engineering.tkachenkoni.gitviewmanager.domain.usecases

import android.util.Log
import com.rsh_engineering.tkachenkoni.gitviewmanager.domain.model_entity.GetReadmeResponse
import com.rsh_engineering.tkachenkoni.gitviewmanager.domain.model_entity.ListLangugesResponse
import com.rsh_engineering.tkachenkoni.gitviewmanager.domain.repository.GitNetworkDetailRepository
import com.rsh_engineering.tkachenkoni.gitviewmanager.domain.repository.GitNetworkRepository
import com.rsh_engineering.tkachenkoni.gitviewmanager.domain.repository.ItemResponsePageListRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody

/**
 *
 * Created by Nikolay Tkachenko on 27, October, 2020
 *
 */
class DetailNetworkUseCase(
    private val repository : GitNetworkDetailRepository
) {

    fun listLanguages(owner:String, userName: String): Single<Map<String, Int>>
            = repository.listLanguages(owner, userName)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    fun getReadme(owner:String, userName: String):  Single<ResponseBody> = repository.getReadme(owner, userName)
        .subscribeOn(Schedulers.io())
        .flatMap { it ->
            Log.d("TESTNETWORK", "readsme it.html_url = ${it.download_url}")
            repository.getReadmeText(it.download_url)
        }
        .observeOn(AndroidSchedulers.mainThread())
}