package com.seuapp.vipme2.data.remote

import com.seuapp.vipme2.data.local.model.Product
import javax.inject.Inject

class RemoteRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun fetchProducts(): List<Product> = apiService.getProducts()
}