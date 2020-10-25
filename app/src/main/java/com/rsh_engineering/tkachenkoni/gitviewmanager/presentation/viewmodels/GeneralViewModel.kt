package com.rsh_engineering.tkachenkoni.gitviewmanager.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.rsh_engineering.tkachenkoni.gitviewmanager.domain.model_entity.ItemResponse
import com.rsh_engineering.tkachenkoni.gitviewmanager.domain.model_entity.SearchResponse
import com.rsh_engineering.tkachenkoni.gitviewmanager.domain.usecases.NetworkUseCase
import com.rsh_engineering.tkachenkoni.gitviewmanager.perPage
import com.rsh_engineering.tkachenkoni.gitviewmanager.startPage
import io.reactivex.Flowable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import javax.inject.Inject

/**
 *
 * Created by Nikolay Tkachenko on 22, October, 2020
 *
 */

class GeneralViewModel @Inject constructor(private val usecase: NetworkUseCase)  : BaseViewModel(){

    private val searchRepositoryCountMutableLiveData = MutableLiveData<Int>()
    private val searchRepositoryMutableLiveData = MutableLiveData<List<ItemResponse>>()

    private var listItems : MutableList<ItemResponse> = mutableListOf<ItemResponse>()
    private var startedUpdate = true

    fun getSearchRepository(value: String) {

        listItems.clear()
        startedUpdate = true

        val disposable = usecase.searchRepositories(value, startPage, perPage )
            .doOnNext {
                Log.d("TESTNETWORK", "getSearchRepository doOnNext")
            }
            .subscribe(
                {result ->
                    Log.d("TESTNETWORK", "getSearchRepository next")
                    Log.d("TESTNETWORK", "result = ${result.items?.count()}")
                },{
                    Log.d("TESTNETWORK", "getSearchRepository Throwable")
                    Log.d("TESTNETWORK", "it = $it")
                },{
                    Log.d("TESTNETWORK", "getSearchRepository Completable")
                },{
                    Log.d("TESTNETWORK", "getSearchRepository Subscribe")
                }
            )

        compositeDisposable.add(disposable)
    }

    fun getLivesearchRepositoryCount() = searchRepositoryCountMutableLiveData

    fun getLivesearchRepository() = searchRepositoryMutableLiveData



}


//{
//    Log.d("TESTNETWORK", "getSearchRepository next")
//
//},{
//    Log.d("TESTNETWORK", "getSearchRepository Throwable")
//
//},{
//    Log.d("TESTNETWORK", "getSearchRepository Completeble")
//
//},{
//    Log.d("TESTNETWORK", "getSearchRepository Subscription" )
//
//}

//object: Observer<SearchResponse> {
//    override fun onNext(list: SearchResponse) {
//        println(list)
//    }
//
//    override fun onComplete() {
//        println("onComplete")
//    }
//
//    override fun onError(e: Throwable) {
//        println("onError")
//    }
//
//    override fun onSubscribe(s: Disposable) {
//        println("onSubscribe")
//    }
//}

//                {
//                Log.d("TESTNETWORK", "getSearchRepository next")
//            },{
//                Log.d("TESTNETWORK", "getSearchRepository Throwable")
//            },{
//                Log.d("TESTNETWORK", "getSearchRepository Completeble")
//            },{
//                Log.d("TESTNETWORK", "getSearchRepository Subscription" )
//            }

//,{
//    Log.d("TESTNETWORK", "getSearchRepository complete")
//}