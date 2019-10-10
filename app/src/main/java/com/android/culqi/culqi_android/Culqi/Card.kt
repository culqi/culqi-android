package com.android.culqi.culqi_android.culqi

data class Card(val card_number: String,
                val cvv: String,
                val expiration_month: Int,
                val expiration_year: Int,
                val email: String)