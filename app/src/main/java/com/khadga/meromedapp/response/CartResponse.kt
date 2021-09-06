package com.khadga.meromedapp.response

import com.khadga.meromedapp.model.Cart


data class CartResponse(
    val success: Boolean? = null,
    val data: List<Cart>?= null
)
