package com.android.culqi.culqi_android.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Token(
    val id: String?,
    val type: String?,
    val creation_date: Long?,
    val email: String?,
    val card_number: String?,
    val last_four: String?,
    val active: Boolean?
)