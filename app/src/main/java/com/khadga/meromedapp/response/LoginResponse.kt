package com.khadga.meromedapp.response

import com.khadga.meromedapp.entity.User

data class LoginResponse(
    val success: Boolean? = null,
    val token: String? = null,
    val data: User? = null

)
