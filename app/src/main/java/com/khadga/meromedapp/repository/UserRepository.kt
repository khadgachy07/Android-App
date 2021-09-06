package com.khadga.meromedapp.repository

import com.khadga.meromedapp.api.ApiRequest
import com.khadga.meromedapp.api.MeroMedApi
import com.khadga.meromedapp.api.ServiceBuilder
import com.khadga.meromedapp.model.AccountModel
import com.khadga.meromedapp.response.LoginResponse
import com.khadga.meromedapp.response.ServerResponse

class UserRepository : ApiRequest() {
    val egApi = ServiceBuilder.buildService(MeroMedApi::class.java)
    suspend fun registerUser(user: AccountModel): ServerResponse {
        return apiRequest {
            egApi.registerUser(user)
        }
    }

    suspend fun loginUser(username: String, password: String): LoginResponse {
        return apiRequest {
            egApi.loginUser(username, password)
        }
    }
}
