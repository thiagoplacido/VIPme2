package com.seuapp.vipme2.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.seuapp.vipme2.data.local.model.Customer
import kotlinx.coroutines.flow.Flow

@Dao
interface CustomerDao {
    @Query("SELECT * FROM customers ORDER BY name ASC")
    fun getAllCustomers(): Flow<List<Customer>>

    @Upsert
    suspend fun upsert(customer: Customer)

    @Query("DELETE FROM customers WHERE id = :customerId")
    suspend fun delete(customerId: Long)
}
