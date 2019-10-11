package com.android.culqi.culqi_android.rest

import com.android.culqi.culqi_android.model.Card
import com.android.culqi.culqi_android.model.Token
import io.reactivex.Single

class TokenRestDataStore {

    fun getToken(card: Card): Single<Token> {
        return TokenApiClient.getApiClient().getToken(card = card)
    }
}