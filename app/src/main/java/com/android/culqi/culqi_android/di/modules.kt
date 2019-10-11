package com.android.culqi.culqi_android.di

import com.android.culqi.culqi_android.rest.TokenRestDataStore
import org.koin.dsl.module

private val mainModule = module {
    //region Datastore
    single {
        TokenRestDataStore()
    }
    //endregion
}


val modules = listOf(
        mainModule
)