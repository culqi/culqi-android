package com.android.culqi.culqi_android.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.culqi.culqi_android.domain.interactor.GetTokenInteractor
import com.android.culqi.culqi_android.presentation.model.CardVM
import com.android.culqi.culqi_android.presentation.model.mapper.TokenVMMapper
import com.android.culqi.culqi_android.presentation.viewmodel.viewstate.MainVS
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.koin.core.KoinComponent

class MainViewModel(
        private val getTokenInteractor: GetTokenInteractor
) : ViewModel(), KoinComponent {

    protected val disposables = CompositeDisposable()

    val state = MutableLiveData<MainVS>()


    private val mTokenVMMapper by lazy { TokenVMMapper() }

    fun getToken(cardVM: CardVM) {
        disposables.add(getTokenInteractor
                .execute(GetTokenInteractor.Params(cardNumber = cardVM.cardNumber,
                        cvv = cardVM.cvv, expirationMonth = cardVM.expirationMonth,
                        expirationYear = cardVM.expirationYear, email = cardVM.email))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    state.value = MainVS.GetToken(mTokenVMMapper.map(it))
                }, {
                    state.value = MainVS.OnError(it)
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}