package com.android.culqi.culqi_android.culqi

import org.json.JSONObject

interface TokenCallback {

    fun onSuccess(token: JSONObject)

    fun onError(error: Exception)

}