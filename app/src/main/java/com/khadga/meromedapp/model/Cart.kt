package com.khadga.meromedapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.khadga.meromedapp.entity.Product

@Entity
data class Cart(
    @PrimaryKey
    var _id: String,
    var product_id: Product,
    var quantity: Int=0,
    val price: Int = 0
)
