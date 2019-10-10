package com.android.culqi.culqi_android.presentation.viewmodel.viewstate

import com.android.culqi.culqi_android.presentation.model.TokenVM

sealed class MainVS {
    class GetToken(val token: TokenVM): MainVS()
    class OnError(val error:Throwable): MainVS()
}