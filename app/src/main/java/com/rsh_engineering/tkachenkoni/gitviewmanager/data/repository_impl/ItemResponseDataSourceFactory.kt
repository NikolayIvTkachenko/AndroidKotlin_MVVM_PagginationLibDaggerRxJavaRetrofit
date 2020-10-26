package com.rsh_engineering.tkachenkoni.gitviewmanager.data.repository_impl

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.rsh_engineering.tkachenkoni.gitviewmanager.App
import com.rsh_engineering.tkachenkoni.gitviewmanager.data.api.NetworkApi
import com.rsh_engineering.tkachenkoni.gitviewmanager.domain.model_entity.ItemResponse
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 *
 * Created by Nikolay Tkachenko on 26, October, 2020
 *
 */
class ItemResponseDataSourceFactory (
    private val request: String,
    private val networkApi: NetworkApi,
    private val compositeDisposable: CompositeDisposable) : DataSource.Factory<Int, ItemResponse>()  {

    val itemsLiveDataSource = MutableLiveData<ItemResponseDataSource>()

    override fun create(): DataSource<Int, ItemResponse> {
        Log.d("TESTNETWORK", "ItemResponseDataSourceFactory create()")
        Log.d("TESTNETWORK", "value = ${request}")
        val itemsDataSource = ItemResponseDataSource(request, networkApi, compositeDisposable)
        itemsLiveDataSource.postValue(itemsDataSource)

        return itemsDataSource
    }
}