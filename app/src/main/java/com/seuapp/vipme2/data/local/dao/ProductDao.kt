package com.seuapp.vipme2.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.seuapp.vipme2.data.local.model.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Query("SELECT * FROM products ORDER BY name ASC")
    fun getAllProducts(): Flow<List<Product>>

    @Upsert
    suspend fun upsert(product: Product)

    @Query("DELETE FROM products WHERE id = :productId")
    suspend fun delete(productId: Long)
}
