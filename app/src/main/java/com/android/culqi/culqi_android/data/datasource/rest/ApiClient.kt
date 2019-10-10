package com.android.culqi.culqi_android.data.datasource.rest

import com.android.culqi.culqi_android.core.network.BaseApiClient
import com.android.culqi.culqi_android.data.datasource.rest.interfaces.ITokenApiClient

object TokenApiClient: BaseApiClient<ITokenApiClient>(ITokenApiClient::class.java)