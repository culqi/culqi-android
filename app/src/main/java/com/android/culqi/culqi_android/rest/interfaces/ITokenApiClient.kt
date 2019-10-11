package com.android.culqi.culqi_android.rest.interfaces

import com.android.culqi.culqi_android.BuildConfig
import com.android.culqi.culqi_android.model.Card
import com.android.culqi.culqi_android.model.Token
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ITokenApiClient {

    @POST("tokens/")
    fun getToken(@Header("Authorization") authorization: String = BuildConfig.AUTHORIZATION,
                 @Header("Content-Type") contentType:String = BuildConfig.CONTENT_TYPE,
                 @Body card: Card): Single<Token>

}