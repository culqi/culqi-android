package com.android.culqi.culqi_android.di

import com.android.culqi.culqi_android.data.datasource.rest.TokenRestDataStore
import com.android.culqi.culqi_android.domain.interactor.GetTokenInteractor
import com.android.culqi.culqi_android.domain.repository.TokenRepository
import com.android.culqi.culqi_android.presentation.viewmodel.MainViewModel
import com.android.culqi.culqi_android.data.repository.TokenRepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val mainModule = module {

    //region ViewModel
    viewModel {
        MainViewModel(get())
    }
    //endregion

    //region Interactor
    single {
        GetTokenInteractor(
                get()
        )
    }
    //endregion

    //region Repository
    single<TokenRepository> {
        TokenRepositoryImpl(get())
    }
    //endregion

    //region Datastore
    single {
        TokenRestDataStore()
    }
    //endregion
}


val modules = listOf(
        mainModule
)