package com.seuapp.vipme2.data.remote

import com.seuapp.vipme2.data.local.model.Product
import retrofit2.http.GET

interface ApiService {
    @GET("products")
    suspend fun getProducts(): List<Product>
}