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


    override fun fetchLiveItemResponsePagedList(request :String, compositeDisposable: CompositeDisposable) {
        Log.d("TESTNETWORK", "ItemResponsePageListRepositoryImpl fetchLiveItemResponsePagedList")
        Log.d("TESTNETWORK", "value = ${request}")

        if(networkApi == null){
            Log.d("TESTNETWORK", "networkApi == null")
        }else{
            Log.d("TESTNETWORK", "networkApi != null")
        }
    }

//    override fun getNetworkState(): LiveData<NetworkState> {
//        return Transformations.switchMap<ItemResponseDataSource, NetworkState>(
//            itemResponseDataSourceFactory.itemsLiveDataSource, ItemResponseDataSource::networkState
//        )
//    }

}