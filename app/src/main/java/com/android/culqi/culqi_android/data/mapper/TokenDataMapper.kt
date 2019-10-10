package com.android.culqi.culqi_android.data.mapper

import com.android.culqi.culqi_android.domain.model.TokenEntity
import com.android.culqi.culqi_android.core.mapper.Mapper
import com.android.culqi.culqi_android.data.datasource.rest.response.TokenResponseData

class TokenDataMapper : Mapper<TokenResponseData, TokenEntity> {
    override fun map(origin: TokenResponseData) = TokenEntity(
            id = origin.id,
            type = origin.type,
            creation_date = origin.creation_date,
            email = origin.email,
            card_number = origin.card_number,
            last_four = origin.last_four,
            active = origin.active
    )
}