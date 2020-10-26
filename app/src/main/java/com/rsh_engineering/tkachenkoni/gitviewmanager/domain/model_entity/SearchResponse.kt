package com.rsh_engineering.tkachenkoni.gitviewmanager.domain.model_entity


import com.rsh_engineering.tkachenkoni.gitviewmanager.domain.model_entity.ItemResponse
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 *
 * Created by Nikolay Tkachenko on 22, October, 2020
 *
 */
@JsonClass(generateAdapter = true)
data class SearchResponse(

    @Json(name = "total_count")
    val totalCount: Int?,

    @Json(name = "incomplete_results")
    val incompleteResults: Boolean?,

    @Json(name = "items")
    val items: List<ItemResponse>?

)