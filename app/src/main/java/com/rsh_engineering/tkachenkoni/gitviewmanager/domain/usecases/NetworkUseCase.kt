package com.rsh_engineering.tkachenkoni.gitviewmanager.domain.usecases

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.rsh_engineering.tkachenkoni.gitviewmanager.data.repository_impl.NetworkState
import com.rsh_engineering.tkachenkoni.gitviewmanager.domain.model_entity.GetReadmeResponse
import com.rsh_engineering.tkachenkoni.gitviewmanager.domain.model_entity.ItemResponse
import com.rsh_engineering.tkachenkoni.gitviewmanager.domain.model_entity.ListLangugesResponse
import com.rsh_engineering.tkachenkoni.gitviewmanager.domain.model_entity.SearchResponse
import com.rsh_engineering.tkachenkoni.gitviewmanager.domain.repository.GitNetworkRepository
import com.rsh_engineering.tkachenkoni.gitviewmanager.domain.repository.ItemResponsePageListRepository
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 *
 * Created by Nikolay Tkachenko on 22, October, 2020
 *
 */
class NetworkUseCase(
    private val repository : GitNetworkRepository,
    private val repositoryPagination : ItemResponsePageListRepository
) {

    fun searchRepositoriesPageList(request : String, compositeDisposable : CompositeDisposable) : LiveData<PagedList<ItemResponse>> {
        Log.d("TESTNETWORK", "NetworkUseCase getSearchRepositoryPageList")
        Log.d("TESTNETWORK", "value = ${request}")
        return repositoryPagination.fetchLiveItemResponsePagedList(request,compositeDisposable)
    }

    fun statusNetwork():LiveData<NetworkState>{
        return repositoryPagination.getNetworkState()
    }


    fun searchRepositories( search: String, page: Int, perPage: Int): Single<SearchResponse> =
        repository.searchRepositories(search, page, perPage)
            .subscribeOn(Schedulers.io())
            .map{ it ->
                Log.d("TESTNETWORK", "NetworkUseCase map")
                Log.d("TESTNETWORK", "it.totalCount = ${it.totalCount}")
                Log.d("TESTNETWORK", "search = ${search}")
                Log.d("TESTNETWORK", "page = ${page}")
                Log.d("TESTNETWORK", "perPage = ${perPage}")
                Log.d("TESTNETWORK", "count = ${it.items?.count()}")
                it
            }.filter {
                it -> it.items!!.count() == perPage
            }
            .flatMapSingle {
                //searchRepositories(search, page + 1, perPage)
                //Single.just(it)
                val sResponse = Single.just(it)
                sResponse
            }
    fun listLanguages(list: String): Single<ListLangugesResponse> = repository.listLanguages(list)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    fun getReadme(getreadme: String): Single<GetReadmeResponse> = repository.getReadme(getreadme)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

