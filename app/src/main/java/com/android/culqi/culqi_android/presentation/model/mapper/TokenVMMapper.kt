package com.android.culqi.culqi_android.presentation.model.mapper

import com.android.culqi.culqi_android.domain.model.TokenEntity
import com.android.culqi.culqi_android.presentation.model.TokenVM
import com.android.culqi.culqi_android.core.mapper.Mapper

class TokenVMMapper : Mapper<TokenEntity, TokenVM> {
    override fun map(origin: TokenEntity) = TokenVM(
            id = origin.id,
            type = origin.type,
            creation_date = origin.creation_date,
            email = origin.email,
            card_number = origin.card_number,
            last_four = origin.last_four,
            active = origin.active
    )
}