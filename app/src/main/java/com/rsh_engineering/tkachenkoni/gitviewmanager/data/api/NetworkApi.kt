package com.rsh_engineering.tkachenkoni.gitviewmanager.data.api

import com.rsh_engineering.tkachenkoni.gitviewmanager.domain.model_entity.GetReadmeResponse
import com.rsh_engineering.tkachenkoni.gitviewmanager.domain.model_entity.ListLangugesResponse
import com.rsh_engineering.tkachenkoni.gitviewmanager.domain.model_entity.SearchResponse
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *
 * Created by Nikolay Tkachenko on 22, October, 2020
 *
 */

interface NetworkApi {

    //?page=2&per_page=100'
    @GET("search/repositories")
    fun searchRepositories(@Query("q") search: String,
                           @Query("page") page : Int,
                           @Query("per_page") perPage : Int): Flowable<SearchResponse>

    @GET("repos")
    fun listLanguages(@Query("list-languages") list: String): Single<ListLangugesResponse>

    @GET("contents")
    fun getReadme(@Query("get-the-readme") getreadme: String): Single<GetReadmeResponse>


    //https://api.github.com/search/repositories?q=tetris+language:assembly&sort=stars&order=desc
    //Status: 304 Not Modified
    //Status: 422 Unprocessable Entity
    //Status: 503 Service Unavailable

    //https://api.github.com/search/repositories?q=topic:ruby+topic:rails
    //https://api.github.com/search/code?q=addClass+in:file+language:js+repo:jquery/jquery
    //https://api.github.com/search/users?q=tom+repos:%3E42+followers:%3E1000

}