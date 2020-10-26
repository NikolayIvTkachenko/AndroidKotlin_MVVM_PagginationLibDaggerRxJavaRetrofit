package com.rsh_engineering.tkachenkoni.gitviewmanager.data.repository_impl

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.rsh_engineering.tkachenkoni.gitviewmanager.data.api.NetworkApi
import com.rsh_engineering.tkachenkoni.gitviewmanager.domain.model_entity.ItemResponse
import com.rsh_engineering.tkachenkoni.gitviewmanager.domain.repository.ItemResponsePageListRepository
import com.rsh_engineering.tkachenkoni.gitviewmanager.perPage
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 *
 * Created by Nikolay Tkachenko on 26, October, 2020
 *
 */
class ItemResponsePageListRepositoryImpl (private val networkApi: NetworkApi):
    ItemResponsePageListRepository {

    lateinit var itemResponsePageList: LiveData<PagedList<ItemResponse>>
    lateinit var itemResponseDataSourceFactory: ItemResponseDataSourceFactory


    override fun fetchLiveItemResponsePagedList(request :String, compositeDisposable: CompositeDisposable): LiveData<PagedList<ItemResponse>> {
        Log.d("TESTNETWORK", "ItemResponsePageListRepositoryImpl fetchLiveItemResponsePagedList")
        Log.d("TESTNETWORK", "value = ${request}")
        itemResponseDataSourceFactory = ItemResponseDataSourceFactory(request, networkApi, compositeDisposable)

        val config = PagedList.Config.Builder()
            .setPrefetchDistance(25)
            .setEnablePlaceholders(false)
            .setPageSize(perPage)
            .build()

        itemResponsePageList = LivePagedListBuilder(itemResponseDataSourceFactory, config).build()

        return itemResponsePageList
    }

    override fun getNetworkState(): LiveData<NetworkState> {
        return Transformations.switchMap<ItemResponseDataSource, NetworkState>(
            itemResponseDataSourceFactory.itemsLiveDataSource, ItemResponseDataSource::networkState
        )
    }
}