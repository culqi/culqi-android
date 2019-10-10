package com.android.culqi.culqi_android.domain.interactor

import com.android.culqi.culqi_android.domain.model.TokenEntity
import com.android.culqi.culqi_android.domain.repository.TokenRepository
import com.android.culqi.culqi_android.core.interactor.Interactor
import io.reactivex.Single

class GetTokenInteractor(
        private val tokenRepository: TokenRepository
) : Interactor<GetTokenInteractor.Params, Single<TokenEntity>> {

    override fun execute(params: Params): Single<TokenEntity> {
        return tokenRepository.getToken(params.cardNumber, params.cvv,params.expirationMonth,params.expirationYear,params.email)
    }

    data class Params(val cardNumber: String, val cvv: String, val expirationMonth: String, val expirationYear: Int, val email: String)
}