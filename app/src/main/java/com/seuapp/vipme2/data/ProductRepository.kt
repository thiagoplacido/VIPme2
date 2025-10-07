package com.seuapp.vipme2.data

import javax.inject.Inject

class ProductRepository @Inject constructor() {
    fun getProducts(): List<String> {
        return listOf("Shampoo", "Sabonete", "Creme", "Perfume")
    }
}