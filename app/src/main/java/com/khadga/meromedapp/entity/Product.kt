package com.khadga.meromedapp.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(
    @PrimaryKey
    var _id: String = "",
    var pName: String? = null,
    var pDesc: String? = null,
    var pPrice: String? = null,
    var pImage: String? = null,
    var pRating: String? = null
)