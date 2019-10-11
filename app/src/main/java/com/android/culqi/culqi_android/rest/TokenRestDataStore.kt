package com.android.culqi.culqi_android.data.datasource.rest

import com.android.culqi.culqi_android.data.datasource.rest.request.Card
import com.android.culqi.culqi_android.data.datasource.rest.response.Token
import com.android.culqi.culqi_android.rest.TokenApiClient
import io.reactivex.Single

class TokenRestDataStore {

    fun getToken(card: Card): Single<Token> {
        return TokenApiClient.getApiClient().getToken(card = card)
    }
}