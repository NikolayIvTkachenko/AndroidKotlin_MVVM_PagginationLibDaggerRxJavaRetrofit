package com.rsh_engineering.tkachenkoni.gitviewmanager.domain.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.rsh_engineering.tkachenkoni.gitviewmanager.data.repository_impl.NetworkState
import com.rsh_engineering.tkachenkoni.gitviewmanager.domain.model_entity.ItemResponse
import io.reactivex.disposables.CompositeDisposable

/**
 *
 * Created by Nikolay Tkachenko on 26, October, 2020
 *
 */
interface ItemResponsePageListRepository {

    fun fetchLiveItemResponsePagedList (request :String, compositeDisposable: CompositeDisposable) //: LiveData<PagedList<ItemResponse>>

    //fun getNetworkState(): LiveData<NetworkState>
}