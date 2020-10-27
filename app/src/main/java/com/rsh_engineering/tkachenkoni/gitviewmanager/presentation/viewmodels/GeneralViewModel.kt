package com.rsh_engineering.tkachenkoni.gitviewmanager.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.rsh_engineering.tkachenkoni.gitviewmanager.data.api.NetworkApi
import com.rsh_engineering.tkachenkoni.gitviewmanager.data.repository_impl.ItemResponseDataSource
import com.rsh_engineering.tkachenkoni.gitviewmanager.data.repository_impl.ItemResponseDataSourceFactory
import com.rsh_engineering.tkachenkoni.gitviewmanager.data.repository_impl.NetworkState
import com.rsh_engineering.tkachenkoni.gitviewmanager.domain.model_entity.ItemResponse
import com.rsh_engineering.tkachenkoni.gitviewmanager.perPage
import javax.inject.Inject

/**
 *
 * Created by Nikolay Tkachenko on 22, October, 2020
 *
 */

class GeneralViewModel @Inject constructor(private val networkApi: NetworkApi)  : BaseViewModel(){

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
}
