package com.android.culqi.culqi_android

import android.app.ProgressDialog
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.android.culqi.culqi_android.culqi.Card
import com.android.culqi.culqi_android.culqi.Token
import com.android.culqi.culqi_android.culqi.TokenCallback
import com.android.culqi.culqi_android.validation.Validation
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    internal lateinit var validation: Validation
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        validation = Validation()

        val progress = ProgressDialog(this)
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

                val cvv = validation.bin(text, tvMainKindCard)
                if (cvv > 0) {
                    etMainCVV.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(cvv))
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

            val card = Card(etMainCardNumber.text.toString(), etMainCVV.text.toString(), 9, 2020, etMainEmail.text.toString())

            val token = Token("pk_test_vzMuTHoueOMlgUPj")

            token.createToken(applicationContext, card, object : TokenCallback {
                override fun onSuccess(token: JSONObject) {
                    try {
                        tvMainTokenId.text = token.get("id").toString()
                    } catch (ex: Exception) {
                        progress.hide()
                    }

                    progress.hide()
                }

                override fun onError(error: Exception) {
                    progress.hide()
                }
            })
        }

    }

}