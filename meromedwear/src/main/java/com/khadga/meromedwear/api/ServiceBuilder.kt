package com.khadga.meromedapp.api

import com.khadga.meromedapp.model.AccountModel
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
      private const val BASE_URL = "http://10.0.2.2:90/api/"
//    private const val BASE_URL = "http://192.168.1.107:90/api/"


    var token: String? = null
    var user: AccountModel? = null

    private val okHttp = OkHttpClient.Builder()
    private val retrofitBuilder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttp.build())

    private val retrofit = retrofitBuilder.build()

    //Generic Function
    fun <T> buildService(serviceType: Class<T>): T {
        return retrofit.create(serviceType)
    }

    fun loadImagePath(): String {
        val arr = BASE_URL.split("/").toTypedArray()
        return arr[0] + "/" + arr[1] + arr[2] + "/"
    }
}