package com.khadga.meromedapp.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey
    var _id: String = "",
    val accFN: String? = null,
    val accUN: String? = null,
    val accEmail: String? = null,
    val accPhone: String? = null,
    val accPwd: String? = null,
    val userType: String = "Customer"
) {

}