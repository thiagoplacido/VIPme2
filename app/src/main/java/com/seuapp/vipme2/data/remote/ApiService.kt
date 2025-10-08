package com.seuapp.vipme2.data.remote 
import retrofit2.http.GET 
import com.seuapp.vipme2.data.local.model.Product 
interface ApiService { 
    @GET("products") 
