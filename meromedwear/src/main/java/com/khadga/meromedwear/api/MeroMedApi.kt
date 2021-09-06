package com.khadga.meromedapp.api

import com.khadga.meromedapp.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface MeroMedApi {
       @FormUrlEncoded
    @POST("account/login")
    suspend fun loginUser(
        @Field("accUN") username: String,
        @Field("accPwd") password: String
    ): Response<LoginResponse>
}