package com.khadga.meromedapp.repository

import com.khadga.meromedapp.api.ApiRequest
import com.khadga.meromedapp.api.MeroMedApi
import com.khadga.meromedapp.api.ServiceBuilder
import com.khadga.meromedapp.response.LoginResponse

class UserRepository : ApiRequest() {
    val egApi = ServiceBuilder.buildService(MeroMedApi::class.java)
    
    suspend fun loginUser(username: String, password: String): LoginResponse {
        return apiRequest {
            egApi.loginUser(username, password)
        }
    }
}
