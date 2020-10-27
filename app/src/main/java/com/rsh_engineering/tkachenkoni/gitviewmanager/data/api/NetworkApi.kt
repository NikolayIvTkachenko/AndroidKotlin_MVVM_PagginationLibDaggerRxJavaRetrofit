package com.rsh_engineering.tkachenkoni.gitviewmanager.data.api

import com.rsh_engineering.tkachenkoni.gitviewmanager.domain.model_entity.GetReadmeResponse
import com.rsh_engineering.tkachenkoni.gitviewmanager.domain.model_entity.ListLangugesResponse
import com.rsh_engineering.tkachenkoni.gitviewmanager.domain.model_entity.SearchResponse
import io.reactivex.Flowable
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

/**
 *
 * Created by Nikolay Tkachenko on 22, October, 2020
 *
 */

interface NetworkApi {

    //?page=2&per_page=100 - standard request
    @GET("search/repositories")
    fun searchRepositories(@Query("q") search: String,
                           @Query("page") page : Int,
                           @Query("per_page") perPage : Int): Single<SearchResponse>

    @GET("repos/{owner}/{name}/languages")
    fun listLanguages(@Path("owner") owner: String , @Path("name")userName: String ): Single<Map<String, Int>>

    @GET("repos/{owner}/{name}/readme")
    fun getReadme(@Path("owner") owner: String , @Path("name") userName: String ): Single<GetReadmeResponse>

    @GET
    fun getReadmeText(@Url url: String): Single<ResponseBody>


}