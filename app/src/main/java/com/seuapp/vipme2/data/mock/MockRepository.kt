package com.seuapp.vipme2.data.mock

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.seuapp.vipme2.data.local.model.Product
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class MockRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun loadProducts(): List<Product> {
        val json = context.assets.open("products.json").bufferedReader().use { it.readText() }
        val type = object : TypeToken<List<Product>>() {}.type
        return Gson().fromJson(json, type)
    }
}