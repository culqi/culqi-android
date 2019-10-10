package com.android.culqi.culqi_android.data.datasource.rest.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TokenResponseData(
    val id: String?,
    val type: String?,
    val creation_date: Long?,
    val email: String?,
    val card_number: String?,
    val last_four: String?,
    val active: Boolean?
)