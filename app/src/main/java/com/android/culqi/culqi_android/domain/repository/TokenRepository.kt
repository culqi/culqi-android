package com.android.culqi.culqi_android.domain.repository

import com.android.culqi.culqi_android.domain.model.TokenEntity
import io.reactivex.Single

interface TokenRepository {
    fun getToken(cardNumber: String, cvv: String, expirationMonth: String, expirationYear: Int, email: String): Single<TokenEntity>
}