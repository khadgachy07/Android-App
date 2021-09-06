package com.khadga.meromedapp.response

import com.khadga.meromedapp.model.AccountModel

data class LoginResponse(
    val success: Boolean? = null,
    val token: String? = null,
    val data: AccountModel? = null

)
