package com.rsh_engineering.tkachenkoni.gitviewmanager.domain.model_entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 *
 * Created by Nikolay Tkachenko on 22, October, 2020
 *
 */
@JsonClass(generateAdapter = true)
data class License(
    @Json(name = "key")
    var key: String?,
    @Json(name = "name")
    var name: String?,
    @Json(name = "url")
    var url: String?,
    @Json(name = "spdx_id")
    var spdxId: String?,
    @Json(name = "node_id")
    var nodeId: String?,
    @Json(name = "html_url")
    var htmlUrl: String?
)