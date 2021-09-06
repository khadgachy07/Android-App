package com.khadga.meromedapp.repository

import com.khadga.meromedapp.api.ApiRequest
import com.khadga.meromedapp.api.ProductApi
import com.khadga.meromedapp.api.ServiceBuilder
import com.khadga.meromedapp.response.AddProductResponse
import com.khadga.meromedapp.response.CartResponse
import com.khadga.meromedapp.response.GlobalResponse
import com.khadga.meromedapp.response.ProductResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody

class ProductRepository : ApiRequest() {
    val egApi = ServiceBuilder.buildService(ProductApi::class.java)

    suspend fun addProduct(name: RequestBody, desc: RequestBody, price: RequestBody, rating: RequestBody, image: MultipartBody.Part): AddProductResponse {
        return apiRequest {
            egApi.addProduct(ServiceBuilder.token!!,name, desc, price, rating, image)
        }
    }

    suspend fun getProducts(): ProductResponse {
        return apiRequest {
            egApi.getProduct()
        }
    }

    suspend fun addToCart(productID: String): GlobalResponse {
        return apiRequest {
            egApi.addToCart(ServiceBuilder.token!!, productID)
        }
    }
    suspend fun deleteFromCart(productID: String): GlobalResponse {
        return apiRequest {
            egApi.deleteFromCart(ServiceBuilder.token!!, productID)
        }
    }

    suspend fun getCart(): CartResponse {
        return apiRequest {
            egApi.getCart(ServiceBuilder.token!!)
        }
    }

    suspend fun updateFromCart(productID: String, quantity: Int): GlobalResponse {
        return apiRequest {
            egApi.updateFromCart(ServiceBuilder.token!!, productID, quantity)
        }
    }


}
