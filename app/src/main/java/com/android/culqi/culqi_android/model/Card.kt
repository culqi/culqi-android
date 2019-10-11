package com.android.culqi.culqi_android.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Card(val card_number: String,
                val cvv: String,
                val expiration_month: String,
                val expiration_year: Int,
                val email: String)