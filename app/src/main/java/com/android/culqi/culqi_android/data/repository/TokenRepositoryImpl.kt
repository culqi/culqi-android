package com.android.culqi.culqi_android.data.repository

import com.android.culqi.culqi_android.data.datasource.rest.TokenRestDataStore
import com.android.culqi.culqi_android.data.mapper.TokenDataMapper
import com.android.culqi.culqi_android.domain.model.TokenEntity
import com.android.culqi.culqi_android.domain.repository.TokenRepository
import io.reactivex.Single

class TokenRepositoryImpl(
    private val tokenRestDataStore: TokenRestDataStore
) : TokenRepository {

    private val mTokenDataMapper by lazy { TokenDataMapper() }

    override fun getToken(cardNumber: String, cvv: String, expirationMonth: String, expirationYear: Int, email: String): Single<TokenEntity> =
            tokenRestDataStore.getToken(cardNumber,cvv,expirationMonth,expirationYear,email).map(mTokenDataMapper::map)


}