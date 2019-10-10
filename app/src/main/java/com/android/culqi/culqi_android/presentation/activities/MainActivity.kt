package com.android.culqi.culqi_android.presentation.activities

import android.app.ProgressDialog
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.android.culqi.culqi_android.R
import com.android.culqi.culqi_android.data.datasource.rest.request.CardRequestData
import com.android.culqi.culqi_android.presentation.model.CardVM
import com.android.culqi.culqi_android.presentation.viewmodel.MainViewModel
import com.android.culqi.culqi_android.presentation.viewmodel.viewstate.MainVS
import com.android.culqi.culqi_android.utils.Validation
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.json.JSONObject

class MainActivity : AppCompatActivity() {


    private val viewModel: MainViewModel by viewModel()
    private lateinit var progress:ProgressDialog

    internal lateinit var validation: Validation
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getViewModelComplete()
        validation = Validation()

        progress = ProgressDialog(this)
        progress.setMessage("Validando informacion de la tarjeta")
        progress.setCancelable(false)
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER)

        etMainCardNumber.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.isEmpty()) {
                    etMainCVV.isEnabled = true
                }
            }

            override fun afterTextChanged(s: Editable) {
                val text = etMainCardNumber.text.toString()
                if (s.length == 0) {
                    etMainCardNumber.setBackgroundResource(R.drawable.border_error)
                }

                if (validation.luhn(text)) {
                    etMainCardNumber.setBackgroundResource(R.drawable.border_sucess)
                } else {
                    etMainCardNumber.setBackgroundResource(R.drawable.border_error)
                }

                val cvv = validation.bin(text)
                tvMainKindCard.text = cvv.first
                if (cvv.second > 0) {
                    etMainCVV.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(cvv.second))
                    etMainCVV.isEnabled = true
                } else {
                    etMainCVV.isEnabled = false
                    etMainCVV.setText("")
                }
            }
        })

        etMainYear.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable) {
                val text = etMainYear.text.toString()
                if (validation.year(text)) {
                    etMainYear.setBackgroundResource(R.drawable.border_error)
                } else {
                    etMainYear.setBackgroundResource(R.drawable.border_sucess)
                }
            }
        })

        etMainMonth.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable) {
                val text = etMainMonth.text.toString()
                if (validation.month(text)) {
                    etMainMonth.setBackgroundResource(R.drawable.border_error)
                } else {
                    etMainMonth.setBackgroundResource(R.drawable.border_sucess)
                }
            }
        })

        btMainCreateToken.setOnClickListener {
            progress.show()
            viewModel.getToken(
                    CardVM(
                            cardNumber = etMainCardNumber.text.toString(),
                            cvv = etMainCVV.text.toString(),
                            expirationMonth = "09",
                            expirationYear = 2020,
                            email = etMainEmail.text.toString()
                    )
            )
        }

    }

    private fun getViewModelComplete() {
        viewModel.state.observe(this, Observer {
            when (it) {
                is MainVS.GetToken -> {
                    progress.hide()
                    tvMainTokenId.text = it.token.id
                }
                is MainVS.OnError -> {
                    progress.hide()
                    Log.d("Error", "${it.error.message}")
                }
            }
        })
    }

}