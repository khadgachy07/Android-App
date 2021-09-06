package com.khadga.meromedapp.api

import com.khadga.meromedapp.response.AddProductResponse
import com.khadga.meromedapp.response.CartResponse
import com.khadga.meromedapp.response.GlobalResponse
import com.khadga.meromedapp.response.ProductResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface ProductApi {
    //for inserting product
    @Multipart
    @POST("product/insert")
    suspend fun addProduct(
        @Header("Authorization") token:String,
        @Part("pName") name : RequestBody,
        @Part("pDesc") desc : RequestBody,
        @Part("pPrice") price : RequestBody,
        @Part("pRating") rating : RequestBody,
        @Part pImage : MultipartBody.Part
    ): Response<AddProductResponse>

//    @FormUrlEncoded
//    @POST("account/login")
//    suspend fun loginUser(
//        @Field("accUN") username: String,
//        @Field("accPwd") password: String
//    ): Response<LoginResponse>

    @GET("product/showAll")
    suspend fun getProduct(
    ): Response<ProductResponse>

    @FormUrlEncoded
    @POST("book/medicine")
    suspend fun addToCart(
        @Header("authorization") token: String,
        @Field("product_id") productID: String,
        @Field("quantity") quantity: Int = 1
    ): Response<GlobalResponse>

    @GET("retrieve/myBookings")
    suspend fun getCart(
        @Header("authorization") token: String,
    ): Response<CartResponse>

    @FormUrlEncoded
    @POST("delete/booking")
    suspend fun deleteFromCart(
        @Header("authorization") token: String,
        @Field("pid") productID: String,
    ): Response<GlobalResponse>

    @FormUrlEncoded
    @POST("update/booking")
    suspend fun updateFromCart(
        @Header("authorization") token: String,
        @Field("pid") productID: String,
        @Field("qty") quantity: Int,
    ): Response<GlobalResponse>
}