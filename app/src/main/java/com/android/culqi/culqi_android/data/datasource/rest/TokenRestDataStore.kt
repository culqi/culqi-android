package com.android.culqi.culqi_android.data.datasource.rest

import com.android.culqi.culqi_android.data.datasource.rest.request.CardRequestData
import com.android.culqi.culqi_android.data.datasource.rest.response.TokenResponseData
import io.reactivex.Single

class TokenRestDataStore {

    fun getToken(cardNumber: String, cvv: String, expirationMonth: String, expirationYear: Int, email: String): Single<TokenResponseData> {
        return TokenApiClient.getApiClient().getToken(cardRequestData = CardRequestData(cardNumber, cvv, expirationMonth, expirationYear, email))
    }
}