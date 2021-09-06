package com.khadga.meromedapp.model

data class AccountModel(
    val _id: String? = null,
    val accFN: String? = null,
    val accUN: String? = null,
    val accEmail: String? = null,
    val accPhone: String? = null,
    val accPwd: String? = null,
    val userType: String = "Customer"
)
