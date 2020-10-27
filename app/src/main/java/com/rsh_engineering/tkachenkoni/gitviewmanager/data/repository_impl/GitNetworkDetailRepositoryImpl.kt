package com.rsh_engineering.tkachenkoni.gitviewmanager.data.repository_impl

import com.rsh_engineering.tkachenkoni.gitviewmanager.data.api.NetworkApi
import com.rsh_engineering.tkachenkoni.gitviewmanager.domain.model_entity.GetReadmeResponse
import com.rsh_engineering.tkachenkoni.gitviewmanager.domain.model_entity.ListLangugesResponse
import com.rsh_engineering.tkachenkoni.gitviewmanager.domain.repository.GitNetworkDetailRepository
import io.reactivex.Single
import okhttp3.ResponseBody

/**
 *
 * Created by Nikolay Tkachenko on 27, October, 2020
 *
 */

class GitNetworkDetailRepositoryImpl (val networkApi: NetworkApi) : GitNetworkDetailRepository {
    override fun listLanguages(owner: String, userName: String): Single<Map<String, Int>> {
        return networkApi.listLanguages(owner, userName)
    }

    override fun getReadme(owner: String, userName: String): Single<GetReadmeResponse> {
        return networkApi.getReadme(owner, userName)
    }

    override fun getReadmeText(url: String):  Single<ResponseBody>{
        return networkApi.getReadmeText(url)
    }
}