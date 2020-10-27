package com.rsh_engineering.tkachenkoni.gitviewmanager.domain.repository

import com.rsh_engineering.tkachenkoni.gitviewmanager.domain.model_entity.GetReadmeResponse
import com.rsh_engineering.tkachenkoni.gitviewmanager.domain.model_entity.ListLangugesResponse
import com.rsh_engineering.tkachenkoni.gitviewmanager.domain.model_entity.SearchResponse
import io.reactivex.Flowable
import io.reactivex.Single

/**
 *
 * Created by Nikolay Tkachenko on 22, October, 2020
 *
 */

interface GitNetworkRepository {

    fun searchRepositories( search: String, page: Int, perPage: Int): Single<SearchResponse>

    fun listLanguages(owner:String, userName: String): Single<ListLangugesResponse>

    fun getReadme(owner:String, userName: String): Single<GetReadmeResponse>

}