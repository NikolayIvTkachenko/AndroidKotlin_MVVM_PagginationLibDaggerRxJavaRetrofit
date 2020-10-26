package com.rsh_engineering.tkachenkoni.gitviewmanager.data.repository_impl

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.rsh_engineering.tkachenkoni.gitviewmanager.data.api.NetworkApi
import com.rsh_engineering.tkachenkoni.gitviewmanager.domain.model_entity.ItemResponse
import com.rsh_engineering.tkachenkoni.gitviewmanager.perPage
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 *
 * Created by Nikolay Tkachenko on 26, October, 2020
 *
 */
class ItemResponseDataSource (private val request: String, private val networkApi : NetworkApi, private val compositeDisposable: CompositeDisposable) : PageKeyedDataSource<Int, ItemResponse>(){

    private var page = 1
    val networkState: MutableLiveData<NetworkState> = MutableLiveData()

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, ItemResponse>
    ) {
        Log.d("TESTNETWORK", "ItemResponseDataSource loadInitial")
        Log.d("TESTNETWORK", "value = ${request}")
        networkState.postValue(NetworkState.LOADING)
        compositeDisposable.add(
            networkApi.searchRepositories(request, page, perPage)
                .subscribeOn(Schedulers.io())
                .subscribe ({
                        result ->

                        Log.d("TESTNETWORK", "loadInitial success result = ${result}")
                        callback.onResult(result?.items!!, null, page+1)

                        networkState.postValue(NetworkState.LOADED)
                },{
                        error ->
                        networkState.postValue(NetworkState.ERROR)
                        Log.d("TESTNETWORK", "secces result = ${error.message}")
                        Log.d("MoviewDataSource", error.message)
                })
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, ItemResponse>) {

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, ItemResponse>) {
        Log.d("TESTNETWORK", "ItemResponseDataSource loadAfter")
        Log.d("TESTNETWORK", "value = ${request}")
        networkState.postValue(NetworkState.LOADING)
        compositeDisposable.add(
            networkApi.searchRepositories(request, page, perPage)
                .subscribeOn(Schedulers.io())
                .subscribe ({ result ->

                    Log.d("TESTNETWORK", "loadAfter success result = ${result}")
                    if(result.totalCount!! >= params.key){
                        callback.onResult(result?.items!!, params.key+1)
                        networkState.postValue(NetworkState.LOADED)
                    }else{

                        networkState.postValue(NetworkState.ENDOFLIST)
                    }
                },{
                    networkState.postValue(NetworkState.ERROR)
                    Log.e("MoviewDataSource", it.message)
                })
        )
    }
}