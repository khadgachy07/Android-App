package com.khadga.meromedapp.api

import com.khadga.meromedapp.model.AccountModel
import com.khadga.meromedapp.response.LoginResponse
import com.khadga.meromedapp.response.ServerResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface MeroMedApi {
    //for registering user
    @POST("account/insert")
    suspend fun registerUser(@Body user: AccountModel): Response<ServerResponse>

    @FormUrlEncoded
    @POST("account/login")
    suspend fun loginUser(
        @Field("accUN") username: String,
        @Field("accPwd") password: String
    ): Response<LoginResponse>
}