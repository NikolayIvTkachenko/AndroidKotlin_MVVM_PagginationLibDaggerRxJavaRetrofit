package com.rsh_engineering.tkachenkoni.gitviewmanager.domain.model_entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 *
 * Created by Nikolay Tkachenko on 22, October, 2020
 *
 */

@JsonClass(generateAdapter = true)
data class ListLangugesResponse(
    @Json(name =  "JavaScript")
    var valueJavaScript: Int,

    @Json(name =  "HTML")
    var valueHTML: Int,

    @Json(name =  "CSS")
    var valueCSS: Int,

    @Json(name =  "C++")
    var valueCPlusPluss: Int,

    @Json(name =  "Java")
    var valueJava: Int,

    @Json(name =  "Swift")
    var valueSwift: Int,

    @Json(name =  "Kotlin")
    var valueKotlin: Int,

    @Json(name =  "ObjectC")
    var valueObjC: Int

)