package com.rsh_engineering.tkachenkoni.gitviewmanager.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.rsh_engineering.tkachenkoni.gitviewmanager.data.repository_impl.NetworkState
import com.rsh_engineering.tkachenkoni.gitviewmanager.domain.model_entity.ItemResponse
import com.rsh_engineering.tkachenkoni.gitviewmanager.domain.model_entity.SearchResponse
import com.rsh_engineering.tkachenkoni.gitviewmanager.domain.usecases.NetworkUseCase
import com.rsh_engineering.tkachenkoni.gitviewmanager.perPage
import com.rsh_engineering.tkachenkoni.gitviewmanager.startPage
import io.reactivex.Flowable
import io.reactivex.Observer
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription
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

    var itemsResponsePagedList : MutableLiveData<PagedList<ItemResponse>> = MutableLiveData()



    fun getNetworkStatus(): LiveData<NetworkState>{
        return usecase.statusNetwork()
    }

    fun getSearchRepositoryPageList(value: String) {
        Log.d("TESTNETWORK", "GeneralViewModel getSearchRepositoryPageList")
        Log.d("TESTNETWORK", "value = ${value}")
        itemsResponsePagedList.value = usecase.searchRepositoriesPageList(value, compositeDisposable).value
    }


//    fun listItemsIsEmpty():Boolean {
//        itemsResponsePagedList?.let{
//            return itemsResponsePagedList.value?.isEmpty() ?: true
//        }
//        return false
//    }


    fun getSearchRepository(value: String) {
        listItems.clear()
        val disposable = usecase.searchRepositories(value, startPage, perPage )
            .doOnSuccess { result ->
                Log.d("TESTNETWORK", "getSearchRepository doOnSuccess")
                Log.d("TESTNETWORK", "getSearchRepository result = ${result.items?.count()}")

            }.doOnError {error ->
                Log.d("TESTNETWORK", "getSearchRepository doOnError")
                Log.d("TESTNETWORK", "getSearchRepository error = ${error}")

            }.flatMap {
                Log.d("TESTNETWORK", "getSearchRepository flatMap")
                usecase.searchRepositories(value, startPage + 1, perPage)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()

       compositeDisposable.add(disposable)
    }

    fun getLivesearchRepositoryCount() = searchRepositoryCountMutableLiveData

    fun getLivesearchRepository() = searchRepositoryMutableLiveData

}

