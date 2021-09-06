package com.khadga.meromedapp.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Cart(
    @PrimaryKey
    var _id: String,
    var product_id: String = "",
    var pName: String? = null,
    var pDesc: String? = null,
    var pPrice: String? = null,
    var pImage: String? = null,
    var pRating: String? = null,
    var quantity: Int=0,
    val price: Int = 0
)
