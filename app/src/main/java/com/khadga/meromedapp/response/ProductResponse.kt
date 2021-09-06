package com.khadga.meromedapp.response

import com.khadga.meromedapp.entity.Product

data class ProductResponse(
    val success: Boolean? = null,
    val data: List<Product>? = null
)
