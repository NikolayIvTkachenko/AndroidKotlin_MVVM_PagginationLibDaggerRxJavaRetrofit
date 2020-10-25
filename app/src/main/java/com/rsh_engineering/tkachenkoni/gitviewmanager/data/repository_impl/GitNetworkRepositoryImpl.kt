package com.rsh_engineering.tkachenkoni.gitviewmanager.data.repository_impl

import com.rsh_engineering.tkachenkoni.gitviewmanager.data.api.NetworkApi
import com.rsh_engineering.tkachenkoni.gitviewmanager.domain.model_entity.GetReadmeResponse
import com.rsh_engineering.tkachenkoni.gitviewmanager.domain.model_entity.ListLangugesResponse
import com.rsh_engineering.tkachenkoni.gitviewmanager.domain.model_entity.SearchResponse
import com.rsh_engineering.tkachenkoni.gitviewmanager.domain.repository.GitNetworkRepository
import io.reactivex.Flowable
import io.reactivex.Single

/**
 *
 * Created by Nikolay Tkachenko on 22, October, 2020
 *
 */

class GitNetworkRepositoryImpl(val networkApi: NetworkApi) : GitNetworkRepository {

    override fun searchRepositories(search: String, page: Int, perPage: Int): Flowable<SearchResponse> {
        return networkApi.searchRepositories(search, page, perPage)
    }

    override fun listLanguages(list: String): Single<ListLangugesResponse> {
       return networkApi.listLanguages(list)
    }

    override fun getReadme(getreadme: String): Single<GetReadmeResponse> {
        return networkApi.getReadme(getreadme)
    }

}