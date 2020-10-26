package com.rsh_engineering.tkachenkoni.gitviewmanager.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.rsh_engineering.tkachenkoni.gitviewmanager.data.api.NetworkApi
import com.rsh_engineering.tkachenkoni.gitviewmanager.data.repository_impl.ItemResponseDataSource
import com.rsh_engineering.tkachenkoni.gitviewmanager.data.repository_impl.ItemResponseDataSourceFactory
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

class GeneralViewModel @Inject constructor(private val networkApi: NetworkApi, private val usecase: NetworkUseCase)  : BaseViewModel(){


    var itemResponsePageList: LiveData<PagedList<ItemResponse>>
    var itemResponseDataSourceFactory: ItemResponseDataSourceFactory
    val config : PagedList.Config

    init {
        config = PagedList.Config.Builder()
            .setPrefetchDistance(25)
            .setEnablePlaceholders(true)
            .setPageSize(perPage)
            .build()
        itemResponseDataSourceFactory = ItemResponseDataSourceFactory("", networkApi, compositeDisposable)
        itemResponsePageList = LivePagedListBuilder(itemResponseDataSourceFactory, config).build()
    }

    fun getNetworkState(): LiveData<NetworkState>{
        return Transformations.switchMap<ItemResponseDataSource, NetworkState>(
            itemResponseDataSourceFactory.itemsLiveDataSource, ItemResponseDataSource::networkState
        )
    }

    fun getSearchRepositoryPageList(lifecycleOwner: LifecycleOwner, searchValue: String) {
        itemResponsePageList.removeObservers(lifecycleOwner)
        Log.d("TESTNETWORK", "GeneralViewModel getSearchRepositoryPageList")
        Log.d("TESTNETWORK", "value = ${searchValue}")
        if (searchValue == null){
            itemResponseDataSourceFactory = ItemResponseDataSourceFactory("", networkApi, compositeDisposable)
        }else{
            itemResponseDataSourceFactory = ItemResponseDataSourceFactory(searchValue, networkApi, compositeDisposable)
        }
        itemResponsePageList = LivePagedListBuilder(itemResponseDataSourceFactory, config).build()

    }

    fun listIsEmpty():Boolean {
        return itemResponsePageList.value?.isEmpty() ?: true
    }

//    fun getSearchRepository(value: String) {
//        val disposable = usecase.searchRepositories(value, startPage, perPage )
//            .doOnSuccess { result ->
//                Log.d("TESTNETWORK", "getSearchRepository doOnSuccess")
//                Log.d("TESTNETWORK", "getSearchRepository result = ${result.items?.count()}")
//
//            }.doOnError {error ->
//                Log.d("TESTNETWORK", "getSearchRepository doOnError")
//                Log.d("TESTNETWORK", "getSearchRepository error = ${error}")
//
//            }.flatMap {
//                Log.d("TESTNETWORK", "getSearchRepository flatMap")
//                usecase.searchRepositories(value, startPage + 1, perPage)
//            }
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe()
//
//       compositeDisposable.add(disposable)
//    }

}
