package com.rsh_engineering.tkachenkoni.gitviewmanager.domain.usecases

import android.util.Log
import com.rsh_engineering.tkachenkoni.gitviewmanager.domain.model_entity.GetReadmeResponse
import com.rsh_engineering.tkachenkoni.gitviewmanager.domain.model_entity.ListLangugesResponse
import com.rsh_engineering.tkachenkoni.gitviewmanager.domain.model_entity.SearchResponse
import com.rsh_engineering.tkachenkoni.gitviewmanager.domain.repository.GitNetworkRepository
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers

/**
 *
 * Created by Nikolay Tkachenko on 22, October, 2020
 *
 */
class NetworkUseCase(private val repository : GitNetworkRepository) {
    fun searchRepositories( search: String, page: Int, perPage: Int): Flowable<SearchResponse> =
        repository.searchRepositories(search, page, perPage)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map{ it ->
                Log.d("TESTNETWORK", "NetworkUseCase map")
                Log.d("TESTNETWORK", "it.totalCount = ${it.totalCount}")
                Log.d("TESTNETWORK", "search = ${search}")
                Log.d("TESTNETWORK", "page = ${page}")
                Log.d("TESTNETWORK", "perPage = ${perPage}")
                Log.d("TESTNETWORK", "count = ${it.items?.count()}")
                it
            }.filter {
                it -> !it.items!!.isEmpty()

            }.flatMap {
                

            }






    fun listLanguages(list: String): Single<ListLangugesResponse> = repository.listLanguages(list)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())


    fun getReadme(getreadme: String): Single<GetReadmeResponse> = repository.getReadme(getreadme)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}


//.doOnSuccess {
//    Log.d("TESTNETWORK", "NetworkUseCase succes")
//
//}.doOnError {
//    Log.d("TESTNETWORK", "NetworkUseCase error")
//
//}