package com.android.culqi.culqi_android.presentation.model

data class CardVM(val cardNumber: String,
                  val cvv: String,
                  val expirationMonth: String,
                  val expirationYear: Int,
                  val email: String)